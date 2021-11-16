<template>
  <v-app id="inspire" class="container">

    <template>

      <div style="position: absolute;top: 20%;left: 10%;">
        <v-img src="../assets/smile.png"style="width: 50px;"></v-img>
      </div >
      <h1 class="ic" style="position: absolute;top: 20%;left: 15%;">
        Willow
      </h1>

<div style="position: absolute;top: 30%;left: 20%;width: 700px;">

      <v-form
          ref="form"
          v-model="valid"
          lazy-validation
      >
        <v-text-field
            v-model="account"
            :counter="20"
            :value="account"
            :rules="accountRules"
            label="账号"
            required
        ></v-text-field>

        <v-text-field
            :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show3 ? 'text' : 'password'"
            name="input-10-2"
            value="wqfasds"
            class="input-group--focused"
            @click:append="show3 = !show3"
            v-model="password"
            :rules="passwordRules"
            :value="password"
            :property="password"
            label="密码"
            required
        ></v-text-field>
        <v-row class="btn-center">
          <v-btn
              id="mm"
              color="success"
              class="mr-4"
              @click="validate"
          >
            登录
          </v-btn>

          <v-btn
              color="error"
              class="mr-4"
              @click="findPassword"
          >
            找回密码
          </v-btn>


        </v-row>

        <v-row class="iv">
          true
        </v-row>
        <v-row>
          <template>
            <v-row justify="center">
              <v-dialog
                  v-model="dialog2"
                  persistent
                  max-width="600px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      class="btn-center"
                      color="yellow"
                      v-bind="attrs"
                      v-on="on"
                  >
                    注册
                  </v-btn>
                </template>
                <v-card>
                  <v-card-text>
                    <v-container>

                      <v-text-field
                          v-model="idcard"
                          label="身份证号"
                          :rules="[v => !!v || '请输入身份证号']"
                          :value="idcard"
                          required
                      ></v-text-field>


                      <v-text-field
                          v-model="phone"
                          :rules="phoneRules"
                          label="手机号"
                          :value="phone"
                          requird
                      ></v-text-field>

                      <v-text-field
                          v-model="mail"
                          :rules="emailRules"
                          label="邮箱"
                          :value="mail"
                          required
                      ></v-text-field>
                      <v-text-field
                          v-model="name"
                          label="姓名"
                          :rules="[v => !!v || '请输入姓名']"
                          :value="name"
                          required
                      ></v-text-field>
                      <v-text-field
                          v-model="address"
                          label="地址"
                          :rules="[v => !!v || '请输入地址（例：山东省济南市历下区山东大学软件学院）']"
                          :value="address"
                          required
                      ></v-text-field>

                      <v-text-field
                          v-model="p"
                          label="密码"
                          :rules="[v => !!v || '请输入密码']"
                          :value="p"
                          required
                      ></v-text-field>

                      <v-select
                          v-model="e7"
                          :items="identiClass"
                          label="我的课程"
                          @change="setI"
                          dense
                          solo
                          required
                          chips
                      ></v-select>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="dialog2 = false"
                    >
                      取消
                    </v-btn>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="register"
                    >
                      确定
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </template>
        </v-row>

        <v-row class="iv">
          true
        </v-row>
        <v-row class="iv">
          true
        </v-row>
        <v-row>
          <template>
            <v-row justify="center">
              <v-dialog
                  v-model="dialog"
                  persistent
                  max-width="600px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      class="btn-center"
                      color="yellow"
                      v-bind="attrs"
                      v-on="on"
                  >
                    修改密码
                  </v-btn>
                </template>
                <v-card>
                  <v-card-text>
                    <v-container>

                      <v-text-field
                          v-model="account2"
                          :rules="accountRules"
                          label="账号"
                          :value="account2"
                          required
                      ></v-text-field>


                      <v-text-field
                          v-model="password2"
                          :rules="passwordRules"
                          label="旧密码"
                          :value="password2"
                          requird
                      ></v-text-field>

                      <v-text-field
                          v-model="password3"
                          :rules="passwordRules"
                          label="新密码"
                          :value="password3"
                          required
                      ></v-text-field>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="dialog = false"
                    >
                      取消
                    </v-btn>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="changePassword"
                    >
                      确定
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </template>
        </v-row>

      </v-form>
</div>

    </template>

  </v-app>


</template>

<script>
import querystring from "querystring";
import swal from "sweetalert2";

export default {
  name: "Login",
  data: () => ({
    dialog: false,
    dialog2: false,

    idcard: '',
    phone: '',
    mail: '',
    address: "",
    p: '',
    name: '',
    identity: -1,

    e7: 0,
    identiClass: [{text: '学生', value: 0}, {text: '教师', value: 1}],
    valid: true,
    show3: false,
    account: '',
    account2: '',
    account3: '',//便于找回密码
    password: '',
    password2: "",
    password3: '',
    accountRules: [
      v => !!v || '请输入账号',
    ],

    passwordRules: [
      v => !!v || '请输入密码',
    ],
    phoneRules: [
      v => !!v || '需要手机号',
      v => (v && v.length == 11) || '个人手机号不是指定位数',
    ],
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+\..+/.test(v) || '需要邮件',
    ],
  }),
  methods: {
    validate() {
      if (this.valid) {
        const _this = this
        this.$axios.post('http://localhost:8800/login', querystring.stringify({
          account: this.account,
          password: this.password
        })).then(res => {
              console.log(res);
              _this.$store.commit("SET_TOKEN", res.data.token)
              _this.$store.commit("SET_IDENTITY", res.data.identify)
              if (res.data.identify === 0) {
                _this.$router.push("/student");
              } else if (res.data.identify === 1) {
                _this.$router.push("/teacher");
              } else if (res.data.identify === 2) {
                _this.$router.push("/open")
              } else {

                swal.fire({
                  title: '登陆失败',
                  text: '请检查账号及密码',
                  icon: 'error',
                  confirmButtonText: '确定'
                })
              }
            }
        );
      } else {
        swal.fire({
          title: '登陆失败',
          text: '请输入账号及密码',
          icon: 'error',
          timer: 2000,
          showConfirmButton: false
        })
      }
    }
    ,
    changePassword: function () {
      this.dialog = false;
      this.$axios.post("http://localhost:8800/changePassword", querystring.stringify({
        account: this.account2,
        oldP: this.password2,
        secondP: this.password3
      })).then(
          res => {
            console.log(res);
            if (res.data.code === 0) {
              swal.fire({
                title: '修改失败，请确认原密码与账号是否正确',
                icon: 'error',
                timer: 2000,
                showConfirmButton: false
              })
            } else if (res.data.code === 1) {
              swal.fire({
                title: '修改成功，请重新登录',
                icon: 'success',
                timer: 500,
                showConfirmButton: false
              })
            } else if (res.data.code === 2) {
              swal.fire({
                title: '修改失败，两次密码相同',
                icon: 'error',
                timer: 2000,
                showConfirmButton: false
              })
            }
          }
      )
    }
    ,
    setI: function () {
      this.identity = this.e7.value;
    },
    findPassword: async function () {
      const {value: password3} = await swal.fire({
        title: '请输入账号',
        input: 'text',
        inputLabel: '账号',
        inputPlaceholder: '请输入账号',
        inputAttributes: {
          maxlength: 50,
          autocapitalize: 'off',
          autocorrect: 'off'
        }
      })

      if (password3.length > 10) {
        this.account3 = password3;

        this.$axios.get("http://localhost:8800/findPassword", {
          params: {
            account: this.account3
          }
        }).then(
            res => {
              console.log(res);
              if (res.data.code === 0) {
                swal.fire({
                  title: '找回失败',
                  icon: 'error',
                  timer: 2000,
                  showConfirmButton: false
                })
              } else if (res.data.code === 1) {
                swal.fire({
                  title: '找回密码文件以发送至邮箱，请查看邮箱，使用该密码前请修改密码',
                  icon: 'success',
                  showConfirmButton: true
                })
              }
            }
        )
      } else {
        await swal.fire({
          title: '检查账号',
          icon: 'error',
          timer: 2000,
          showConfirmButton: false
        })
      }
    }
    ,
    register: function () {
      this.dialog2 = false;
      if (this.idcard === '') {
        swal.fire({
          title: '注册失败，请输入完整信息',
          icon: 'error',
          timer: 2000,
          showConfirmButton: false
        })
      }
      this.$axios.post("http://localhost:8800/register", querystring.stringify({
        idCard: this.idcard,
        phone: this.phone,
        password: this.p,
        mail: this.mail,
        address: this.address,
        name: this.name,
        identify: this.identity,
      })).then(
          res => {
            console.log(res);
            if (res.data.code === 0) {
              swal.fire({
                title: '注册失败，请检查是否重复注册或格式有误',
                icon: 'error',
                timer: 2000,
                showConfirmButton: false
              })
            } else if (res.data.code === 1) {
              swal.fire({
                title: '注册成功，您的账号是' + res.data.data + "，请妥善保管",
                icon: 'success',
                showConfirmButton: true
              })
            }
          }
      )
    },

  },
}
</script>

<style scoped>
.btn-center {
  display: flex;
  justify-content: center;
}

.teal {
  align-items: center;
}

.ic {

  color: cadetblue;
  font-style: italic;
  align-items: center;
}

.iv {
  color: #fafffb;
}
</style>