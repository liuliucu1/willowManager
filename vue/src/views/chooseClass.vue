<template>
  <v-app id="inspire" class="container">
    <template>
      <h1 class="ic">
        Willow
      </h1>
      <v-row>
        <v-col
            cols="12"
            sm="6"
        >
          <v-date-picker
              v-model="dates"
              range
          ></v-date-picker>
        </v-col>
        <v-col
            cols="12"
            sm="6"
        >
          <v-text-field
              v-model="dateRangeText"
              label="Date range"
              prepend-icon="mdi-calendar"
              readonly
          ></v-text-field>
        </v-col>
        <v-col>
          <v-btn
              outlined
              plain
              raised
              @click="startClass">
            开始选课
          </v-btn>
          <v-btn
              outlined
              plain
              raised
              @click="endClass">
            结束选课
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-btn
        @click="annotate">
          退出登录
        </v-btn>
      </v-row>
    </template>
  </v-app>
</template>

<script>

import sweet from "sweetalert2";


export default {
  name: "chooseClass",
  data: () => ({
    dates: [],
    silly: false,
  }),
  computed: {
    dateRangeText() {
      return this.dates.join(' ~ ')
    },
  },
  methods: {
    judge: function () {
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      var k = this.dates[0].split('-');
      if (k[0] >= year) {
        this.silly = true;
      } else if (k[1] >= month) {
        this.silly = true;
      } else if (k[2] >= strDate) {
        this.silly = true;
      }
    },
    startClass: function () {
      this.judge();
      if (this.silly) {
        this.$axios.get('http://localhost:8800/setStart', {
          params: {
            startTime: this.dates[0],
            endTime: this.dates[1]
          }
        }).then(
            res => {
              sweet.fire({
                title: '设置成功',
                icon: 'success',
                timer: 500,
                showConfirmButton: false
              })
            }
        ).catch();
      } else {
        sweet.fire({
          title: '检查日期是否过期',
          icon: 'error',
          timer: 1000,
          showConfirmButton: false
        })
      }

    },
    endClass: function () {
      this.$axios.get('http://localhost:8800/setStart').then(
          res => {
            sweet.fire({
              title: '取消成功',
              icon: 'success',
              timer: 500,
              showConfirmButton: false
            })
          }
      ).catch();
    },
    annotate: function () {
      this.$store.commit("REMOVE_INFO");
      this.$router.push("/login");
    },
  }
}
</script>

<style scoped>

</style>