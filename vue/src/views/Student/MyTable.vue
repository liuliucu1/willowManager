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
            <v-list-item-title>我的课表</v-list-item-title>
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
            :sort-by="['week']"
            :sort-desc="[true]"
            multi-sort
            class="elevation-1"
        ></v-data-table>
      </template>
    </v-main>
  </v-app>
</template>

<script>
import querystring from "querystring";

export default {
  name: "MyTable",
  data: () => ({
    drawer: true,
    headers: [
      {
        text: '课程名',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      {text: '星期', value: 'week'},
      {text: '课次', value: 'day'},
      {text: '备注', value: 'remark'},
    ],
    desserts: [],
  }),
  created() {
    this.getTable();
  },
  methods: {
    getInfo: function () {
      this.$router.push("/student/info");
    },
    getCourse: function () {
      this.$router.push("/student/selectCourse");
    },
    getScore: function () {
      this.$router.push("/student/lookScore")
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    },
    getTable: function () {
      this.$axios.post('http://localhost:8800/getCourseC', querystring.stringify({
        token: localStorage.getItem("token")
      })).then(
          res => {
            console.log(res);
            for (let i = 0; i < res.data.data.length; i++) {
              console.log(querystring.stringify(querystring.parse(res.data)))
              this.desserts.push({
                name: res.data.data[i].name,
                week: res.data.data[i].week,
                day: res.data.data[i].time_class,
                remark:res.data.data[i].remark,
              })
            }

          })
    }
  }
}
</script>

<style scoped>
.ww {
  color: cadetblue;
  font-style: italic;
}
</style>