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
            <v-list-item-title>我的成绩</v-list-item-title>
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
      <v-row align="center">
        <v-col
            class="d-flex"
            cols="12"
            sm="6"
        >
          <v-select
              v-model="e7"
              :items="semesters"
              label="开课学期"
              @change="setSemester"
              dense
              solo
              required
              chips
          ></v-select>
        </v-col>
        <v-col>
          <v-btn
              @click="lookScore">
            确定
          </v-btn>
        </v-col>
      </v-row>
      <template>
        <v-data-table
            :headers="headers"
            :items="course"
            :items-per-page="5"
            class="elevation-1"
        ></v-data-table>
      </template>
    </v-main>
  </v-app>

</template>

<script>
import querystring from "querystring";
import sweet from "sweetalert2"

export default {
  name: "MyScore",
  data: () => ({
    e7: [],
    len: '',
    semesters: [
      '2020-1',
      '2020-2',
      '2021-1',
      '2021-2',
    ],
    drawer: true,
    headers: [
      {
        text: '课程',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      {text: '学期', value: 'semester'},
      {text: '成绩', value: 'score'},
    ],
    course: []
  }),

  mounted() {
    this.datetimepicker();
  },
  methods: {
    getInfo: function () {
      if (this.$route.path !== '/student/info')
        this.$router.push("/student/info");
    },
    getCourse: function () {
      this.$router.push("/student/selectCourse");
    },
    mytable: function () {
      this.$router.push("/student/mytable");
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    },
    setSemester() {
      this.course.semester = this.e7;
    },
    lookScore: function () {
      this.$axios.post("http://localhost:8800/lookScore", querystring.stringify(
          {
            token: localStorage.getItem("token"),
            semester: this.course.semester,
          }
      )).then(res => {
        console.log(res);
        this.len = res.data.length;
        if (this.len === 0) {
          sweet.fire({
            text: "无数据"
          })
        }
        for (let i = 0; i < res.data.length; i++) {
          this.course.push({
            name: res.data[i].course_name,
            score: res.data[i].score,
            semester: res.data[i].semester,
          })
        }
      })

    },
  }
}
</script>c

<style scoped>
.ww {
  color: cadetblue;
  font-style: italic;
}

.text-left {
  font-size: large;
  color: black;
}
</style>