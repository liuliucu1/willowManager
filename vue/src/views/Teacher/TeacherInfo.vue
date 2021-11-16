<template>
  <div id="app">
    <v-navigation-drawer
        v-model="drawer"
        app
        light
        bottom
    >
      <v-list>
        <v-list-item-group
            active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-title >我的资料</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title @click="getCourse">选课系统</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title @click="annotate">退出登录</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title class="ww"
      >Willow
      </v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-card
          color="blue-grey darken-1"
          dark
          :loading="isUpdating"
      >
        <template v-slot:progress>
          <v-progress-linear
              absolute
              color="green lighten-3"
              height="4"
              indeterminate
          ></v-progress-linear>
        </template>
        <v-img
            height="200"
            src="https://cdn.vuetifyjs.com/images/cards/dark-beach.jpg"
        >
          <v-row>
            <v-col
                class="text-right"
                cols="12"
            >
              <v-menu
                  bottom
                  left
                  transition="slide-y-transition"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      icon
                      v-bind="attrs"
                      v-on="on"
                  >
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item @click="isUpdating = true">
                    <v-list-item-action>
                      <v-icon>mdi-cog</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>Update</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-col>
          </v-row>
        </v-img>
        <v-form>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
                  md="6"
              >
                <v-text-field
                    v-model="name"
                    disabled
                    :value="name"
                    filled
                    color="blue-grey lighten-2"
                    label="Name"
                ></v-text-field>
              </v-col>
              <v-col
                  cols="12"
                  md="6"
              >
                <v-text-field
                    v-model="phone"
                    :disabled="isUpdating"
                    filled
                    :rules="phoneRules"
                    :value="phone"
                    :property="phone"
                    color="blue-grey lighten-2"
                    label="手机号"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col
                  cols="20"
                  md="8">
                <v-text-field
                    v-model="idcard"
                    disabled
                    filled
                    color="blue-grey lighten-2"
                    label="身份证"
                >
                </v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col
                  cols="5"
                  md="8">
                <v-text-field
                    v-model="ages"
                    disabled
                    filled
                    color="blue-grey lighten-2"
                    label="年龄"
                >
                </v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
        <v-divider></v-divider>
        <v-card-actions>
          <v-switch
              v-model="autoUpdate"
              :disabled="isUpdating"
              class="mt-0"
              color="green lighten-2"
              hide-details
              label="Auto Update"
          ></v-switch>
          <v-spacer></v-spacer>
          <v-btn
              :disabled="autoUpdate"
              :loading="isUpdating"
              color="blue-grey darken-3"
              depressed
              @click="updateInfo"
          >
            <v-icon left>
              mdi-update
            </v-icon>
            Update Now
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-main>

  </div>
</template>


<script>
import querystring from "querystring";
import sweet from "sweetalert2"

export default {
  name: "Info",

  data: () => ({
    drawer: true,
    autoUpdate: true,
    isUpdating: false,
    name: '',
    phone: '',
    phoneB:'',
    idcard: '',
    ages: '',
    phoneRules: [
      v => !!v || '需要手机号',
      v => (v && v.length == 11) || '个人手机号不是指定位数',
    ],
  }),

  watch: {
    isUpdating(val) {
      if (val) {
        setTimeout(() => (this.isUpdating = false), 3000)
      }
    },
  },
  created: function () {
    this.getInfo();
  },
  methods: {
    getInfo() {
      const h = this;
      var tokens = localStorage.getItem("token")
      this.$axios.get('http://localhost:8800/info', {params: {token: tokens}}).then(function (response) {
        console.log(response);
        h.name = response.data.name;
        h.idcard = response.data.idcard;
        h.phone = response.data.phone;
        h.ages = response.data.age;
        h.phoneB=response.data.phone;
      })
    },
    updateInfo() {
      console.log(this.phone)
      this.$axios.post('http://localhost:8800/fixInfo', querystring.stringify({
        phone: this.phone,
        token: localStorage.getItem("token")
      })).then(res => {
        console.log(res)
        if (res.data.code === 0) {
          this.drawer2 = true;
          sweet.fire({
            icon: "error",
            text:"修改失败,请检查手机是否正确",
            timer: 2000,
            showConfirmButton: false
          })
          this.phone=this.phoneB
        }else{
          sweet.fire({
            icon: "success", timer: 2000,
            showConfirmButton: false
          })
        }
        this.$store.commit("remove_user");
      })
    },
    remove(item) {
      const index = this.friends.indexOf(item.name)
      if (index >= 0) this.friends.splice(index, 1)
    },

    getCourse: function () {
      this.$router.push("/teacher");
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    }
  },
}
</script>

<style scoped>
.ww {
  color: cadetblue;
  font-style: italic;
}
</style>