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
  <v-card id="lateral"
          height="300"
  >
    <v-toolbar
        dark
        tabs
        flat
        color="indigo"
    >

      <template v-slot:extension>
        <v-tabs
            v-model="tabs"
            align-with-title
        >
          <v-tab href="/teacher/MyCourse">
            我的课程信息
          </v-tab>
          <v-tab href="/teacher/addCourse">
            添加课程
          </v-tab>
          <v-tab href="/teacher/lookScore">
            学生成绩
          </v-tab>
          <v-tabs-slider color="pink"></v-tabs-slider>
        </v-tabs>
      </template>
    </v-toolbar>
    <v-card-text>
      <v-tabs-items v-model="tabs">
        <v-tab-item
            v-for="content in ['one', 'two', 'three']"
            :key="content"
            :value="content"
        >
        </v-tab-item>
      </v-tabs-items>
    </v-card-text>

  </v-card>
</v-main>
  </v-app>
</template>
<script>
export default {
  name: "TeacherCourse",
  data: () => ({
    drawer: true,
    fab: false,
    hidden: false,
    tabs: null,
  }),

  methods:{
    getInfo: function () {
      this.$router.push("/teacher/info");
    },
    getCourse: function () {
      this.$router.push("/teacher");
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    }
  }
}
</script>

<style>
#lateral .v-btn--example {
  bottom: 0;
  position: absolute;

}
.ww {
   color: cadetblue;
   font-style: italic;
 }
</style>