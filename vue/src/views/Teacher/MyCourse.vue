<template>
  <v-app id="inspire">
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
            <v-list-item-title @click="getInfo">我的资料</v-list-item-title>
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
      <template>
        <v-data-table
            :headers="headers"
            :items="desserts"
            class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar
                flat
            >
              <v-divider
                  class="mx-4"
                  inset
                  vertical
              ></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
                  v-model="dialog"
                  max-width="500px"
              >
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{formTitle}}</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                          <v-text-field
                              v-model="remark"
                              v-bind="remark"
                              label="备注"
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="bluedarken-1"
                        text
                        @click="save"
                    >
                      保存
                    </v-btn>
                    <v-btn
                        color="bluedarken-1"
                        text
                        @click="close"
                    >
                      取消
                    </v-btn>

                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDelete" max-width="500px">
                <v-card>
                  <v-card-title class="text-h5">确定要删除课程吗？数据将不会备份</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeDelete">取消</v-btn>
                    <v-btn color="blue darken-1" text @click="deleteItemConfirm">确定</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogSearch" max-width="500px">
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{ formTitle }}</span>
                  </v-card-title>
                  <v-card-text>
                    <template>
                      <div>
                        <v-data-table
                            :headers="headers2"
                            :items="desserts2"
                        >

                          <template v-slot:item.score="props">
                            <v-edit-dialog
                                :return-value.sync="props.item.score"
                                large
                                persistent
                                @save="save2(props.item)"
                                @cancel="cancel"
                                @close="close2"
                            >
                              <div>{{ props.item.score }}</div>
                              <template v-slot:input>
                                <div class="mt-4 text-h6">
                                  输入成绩
                                </div>
                                <v-text-field
                                    v-model="props.item.score"
                                    label="Edit"
                                    single-line
                                    counter
                                    autofocus
                                ></v-text-field>
                              </template>
                            </v-edit-dialog>
                          </template>
                        </v-data-table>

                      </div>
                    </template>

                  </v-card-text>

                </v-card>
              </v-dialog>

            </v-toolbar>
          </template>
          <template v-slot:item.actions="{ item }">
            <v-icon
                small
                class="mr-2"
                @click="editItem(item)"
            >
              mdi-pencil
            </v-icon>
            <v-icon
                small
                class="mr-2"
                @click="deleteItem(item)"
            >
              mdi-delete
            </v-icon>
            <v-icon
                small
                class="mr-2"
                aria-label="My Account" role="img" aria-hidden="false"
                @click="searchItem(item)"
            >
              mdi-account
            </v-icon>
          </template>
          <template v-slot:no-data>
            <v-btn
                color="primary"
                @click="initialize"
            >
              无数据
            </v-btn>
          </template>
        </v-data-table>
      </template>
    </v-main>
  </v-app>

</template>

<script>
import querystring from "querystring";
import sweet from "sweetalert2";

export default {
  name: "MyCourse",
  data: () => ({
    drawer: true,
    id: '',
    remark: '',
    search: '',
    dialog: false,
    dialogSearch: false,
    dialogDelete: false,
    ScoreId: 0 ,
    headers2: [
      {
        text: '学生学号',
        align: 'start',
        sortable: false,
        value: 'id',
      },
      {text: '学生姓名', value: 'name'},
      {text: '成绩', value: 'score'},
    ],
    desserts2: [],
    headers: [
      {
        text: '课程名',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      {text: '地点', value: 'location'},
      {text: '备注', value: 'remark'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    desserts: [],
    editedIndex: -1,

    defaultItem: {
      course_id: '',
      name: '',
      location: '',
      remark: '',
    },
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : '编辑'
    },
  },
  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  created() {
    this.initialize()
  },
  methods: {
    filterOnlyCapsText(value, search, item) {
      return value != null &&
          search != null &&
          typeof value === 'string' &&
          value.toString().toLocaleUpperCase().indexOf(search) !== -1
    },
    initialize() {
      this.$axios.post('http://localhost:8800/getMyC', querystring.stringify({
        token: localStorage.getItem("token"),
        semester: "2021-2"
      })).then(
          response => {
            console.log(response);
            for (let i = 0; i < response.data.length; i++) {
              this.desserts.push({
                id: response.data[i].course_id,
                name: response.data[i].name,
                location: response.data[i].location,
                remark: response.data[i].remark
              })
            }

          }
      )
    },
    getStudentName: function (item) {
      this.desserts2=[];
      this.ScoreId=item.id;
      this.$axios.get('http://localhost:8800/studentName', {params: {courseid: item.id}}).then(
          response => {
            console.log(response);
            if (response.data.code==0){
              sweet.fire({
                text:"选课还未结束，名单正在生成中"
              })
            }else{
              for (let i = 0; i < response.data.data.length; i++) {
                if (response.data.data[i].score!==0){
                  this.desserts2.push({
                    id:response.data.data[i].id,
                    name:response.data.data[i].name,
                    score:response.data.data[i].score,
                  })
                }else{
                  this.desserts2.push({
                    id:response.data.data[i].id,
                    name:response.data.data[i].name,
                    score:0,
                  })
                }

              }
            }
          }
      )
    },

    getInfo: function () {
      this.$router.push("/teacher/info");
    },

    getCourse: function () {
      this.$router.push("/teacher");
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    },
     saveScore:function (item) {
       this.$axios.post('http://localhost:8800/putScore', querystring.stringify({
         score:item.score,
         courseId:this.ScoreId,
         account:item.id
       })).then(
           response => {
             console.log(response);
           }
       )
     },
    deleteTheCourse(value) {
      this.$axios.post('http://localhost:8800/delete', querystring.stringify({course_id: value})).then(res => {
        sweet.fire({
          icon: "success",
          timer: 1000
        })
      })
    },
    editTheCourse() {
      this.$axios.post('http://localhost:8800/changeRemark', querystring.stringify({
        course_id: this.id,
        remark: this.remark,
      })).then(res => {
        sweet.fire({
          icon: "success",
          timer: 1000
        })
      })
    },
    editItem(item) {
      this.id = item.id;
      this.editedIndex = this.desserts.indexOf(item)
      this.dialog = true
    },

    deleteItem(item) {
      this.id = item.id;

      this.dialogDelete = true

    },

    searchItem(item){
      this.getStudentName(item);
      this.id = item.id;
      this.dialogSearch = true
    },
    deleteItemConfirm() {
      // this.desserts.splice(this.editedIndex, 1)
      this.deleteTheCourse(this.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
        this.editTheCourse()
      } else {
        this.desserts.push(this.editedItem)
      }
      this.close()
    },

    save2 (item) {
    this.saveScore(item);
    },
    cancel () {
    },
    close2 () {

    },
  }
}

</script>

<style scoped>

</style>