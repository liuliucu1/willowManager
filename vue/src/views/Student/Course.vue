<template>

  <v-app id="inspire">
    <v-navigation-drawer
        v-model="drawer"
        app
        light
        bottom
    >
      <v-list>
        <v-list-item-group>
<!--            active-class="deep-purple&#45;&#45;text text&#45;&#45;accent-4"-->
<!--        >-->
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

  </v-app>

</template>

<script>
export default {
  name: "Course",
  data: () => ({
    drawer: true,
  }),
  methods: {
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