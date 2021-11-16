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
            <v-list-item-title @click="mytable">我的课表</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title @click="getScore">我的成绩</v-list-item-title>
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
            sort-by="calories"
            class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar
                flat
            >
              <v-toolbar-title>选课中心</v-toolbar-title>
              <v-divider
                  class="mx-4"
                  inset
                  vertical
              ></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogAdd" max-width="500px">
                <v-card>
                  <v-card-title class="text-h5">请确认选课</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1"
                           text @click="addItemConfirm">确认</v-btn>
                    <v-btn color="blue darken-1" text @click="closeAdd">取消</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogDelete" max-width="500px">
                <v-card>
                  <v-card-title class="text-h5">确认退选吗？</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green" text @click="deleteItemConfirm">确认</v-btn>
                    <v-btn color="green" text @click="closeDelete">取消</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:item.actions="{ item }">

            <v-btn
                @click="addItem(item)">
              选课
            </v-btn>

            <v-btn
                @click="deleteItem(item)"
            >
              退选
            </v-btn>

          </template>

        </v-data-table>
      </template>
    </v-main>
  </v-app>

</template>

<script>

import querystring from "querystring";
import sweet from "sweetalert2"
export default {
  name: "SelectCourse",
  data: () => ({
        code:0,
        item:{},
        deleteRan: '',
        drawer: true,
        search: '',
        headers: [
          {
            text: '课程名称',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          {text: '课程类型', value: 'kind'},
          {text: '课程时间', value: 'week'},
          {text: '课次', value: 'time'},
          {text: '学分', value: 'fee'},
          {text: '地点', value: 'location'},
          {text: '授课教师', value: 'teacher'},
          {text: '课程备注', value: 'remark'},
          {text: "已选", value: 'isChoose'},
          {text: '选课退课', value: 'actions', sortable: false},
        ],
        desserts: [],
        dialog: false,
        dialogDelete: false,
        dialogAdd: false,
      }
  ),


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
      const that = this;
      this.$axios.post('http://localhost:8800/getAllCourse',querystring.stringify({
        token:localStorage.getItem("token")
      })).then(
          function (response) {
            console.log(response);
            if (response.data==="-1"){
              sweet.fire({
                text:"未到选课时间，通道关闭"
              })
            }else{
              for (let i = 0; i < response.data.length; i++) {
                if (response.data[i].isChoose) {
                  that.desserts.push(
                      {
                        id: response.data[i].course_id,
                        name: response.data[i].name,
                        kind: response.data[i].kind,
                        week: response.data[i].week,
                        time: response.data[i].time_class,
                        location: response.data[i].location,
                        fee: response.data[i].fee,
                        remark: response.data[i].remark,
                        week_start: response.data[i].week_start,
                        week_end: response.data[i].week_end,
                        teacher: response.data[i].teacherName,
                        isChoose: '已选'
                      })
                } else {
                  that.desserts.push(
                      {
                        id: response.data[i].course_id,
                        name: response.data[i].name,
                        kind: response.data[i].kind,
                        week: response.data[i].week,
                        time: response.data[i].time_class,
                        location: response.data[i].location,
                        fee: response.data[i].fee,
                        remark: response.data[i].remark,
                        week_start: response.data[i].week_start,
                        week_end: response.data[i].week_end,
                        teacher: response.data[i].teacherName,
                      })
                }

              }
            }

          }
      )
    },
    getInfo: function () {
      this.$router.push("/student/info");
    },
    getCourse: function () {
      this.$router.push("/student/selectCourse");
    },
    mytable: function () {
      this.$router.push("/student/mytable");
    },
    getScore: function () {
      this.$router.push("/student/lookScore")
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    },
    chooseC: function (item) {

      console.log("click" + item.name)
    },
    deleteCourse: function (value) {
      this.$axios.post('http://localhost:8800/quit', querystring.stringify({
        course_id: value,
        token: localStorage.getItem("token")
      })).then(
          sweet.fire({
            icon:"success",
            timer:1000,
            showConfirmButton: false
          })
      )
    },
    addCourse: function (value) {
      this.$axios.post('http://localhost:8800/chooseCourse', querystring.stringify({
        course_id: value,
        token: localStorage.getItem("token")
      })).then(
          res=>{
            console.log(res)
            if (res.data.code===1){
              this.code=1;
              sweet.fire({
                icon:"success",
                timer:1000,
                showConfirmButton: false
              })
            }else {
              this.code=2;
              sweet.fire({
                icon:"error",
                timer:1000,
                text:"选课冲突",
                showConfirmButton: false
              })
            }
          }

      )
    },
    deleteItem(item) {
      if (item.isChoose==="已选"){
        this.dialogDelete = true;
        this.deleteRan = item.id;
        this.item=item;
      }else{
        sweet.fire({
          icon:"warning",
          timer:1000,
          text:"未选择，无需删除",
          showConfirmButton: false
        })
      }
    },
    addItem(item) {
      if (item.isChoose==="已选"){
        sweet.fire({
          icon:"warning",
          timer:1000,
          text:"已选择，无需重复",
          showConfirmButton: false
        })
        this.closeAdd();
      }
      else {
        this.dialogAdd = true;
        this.deleteRan = item.id;
        this.item = item;
      }
    },
    deleteItemConfirm() {
      this.deleteCourse(this.deleteRan);
      sweet.fire({
        icon:"success",
        timer:1000
      })
      this.item.isChoose='';
      this.closeDelete()
    },
    addItemConfirm() {
      this.addCourse(this.deleteRan);
      if (this.code===2){
       return;
      }
      this.item.isChoose='已选';
      sweet.fire({
        icon:"success",
        timer:1000
      })
      this.closeAdd()
    },

    close() {
      this.dialog = false

    },
    closeAdd() {
      this.dialogAdd = false
    },
    closeDelete() {
      this.dialogDelete = false
    },
  },
};
</script>

<style scoped>
.ww {
  color: cadetblue;
  font-style: italic;
}

.btn_s {
  color: cadetblue;
}
</style>