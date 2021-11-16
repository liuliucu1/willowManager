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

      <v-toolbar-title class="ww">
        Willow
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
              :items="classes"
              label="我的课程"
              @change="setSemester"
              dense
              solo
              required
              chips
          ></v-select>

        </v-col>
      </v-row>

      <v-chart
          v-if="pic"
          class="chart"

          :option="option"/>
    </v-main>
  </v-app>
</template>

<script>
import {use} from "echarts/core";
import {CanvasRenderer} from "echarts/renderers";
import {PieChart} from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart, {THEME_KEY} from "vue-echarts";
import querystring from "querystring";

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);


export default {

  name: "StudentScore",
  components: {
    VChart
  },
  data: () => ({
    drawer: true,
    pic: false,
    chartPie: null,
    id: 0,
    e7: 0,
    judgeId: 0,
    k1: 1,
    k2: 0,
    k3: 0,
    k4: 0,
    k5: 0,
    classes: [],
    myJudge: [],
    chosen: '',
    option: {
      title: {
        text: "成绩分布比",
        left: "center"
      },
      tooltip: {
        trigger: "item",
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: "vertical",
        left: "left",
        data: [
          "90-100",
          "80-90",
          "70-80",
          "60-70",
          "<60"
        ]
      },
      series: [
        {
          name: "成绩分布",
          type: "pie",
          radius: "55%",
          center: ["50%", "60%"],
          data: [
            {value: 2, name: "90-100"},
            {value: 'k2', name: "80-90"},
            {value: 'k3', name: "70-80"},
            {value: 'k4', name: "60-70"},
            {value: 'k5', name: "<60"}
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)"
            }
          }
        }
      ]
    }
  }),

  created() {
    this.getClass()
  },
  methods: {

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
    getClass: function () {
      const that = this;
      this.$axios.post('http://localhost:8800/getMyC', querystring.stringify({
        token: localStorage.getItem("token")
      })).then(res => {
        console.log(res);
        for (let i = 0; i < res.data.length; i++) {
          that.classes.splice(i, 0, res.data[i].name);
          that.myJudge.push({
            name: res.data[i].name,
            id: res.data[i].course_id
          })
        }
      }).catch();
    },
    setSemester: function () {
      this.chosen = this.e7;
      for (let i = 0; i < this.myJudge.length; i++) {
        if (this.chosen === this.myJudge[i].name) {
          this.judgeId = this.myJudge[i].id;
        }
      }
      this.getIndex();
      this.pic = true
    },
    getIndex: function () {

      this.$axios.get('http://localhost:8800/analyzeScore', {params: {courseId: this.judgeId}}).then(
          response => {
            console.log(this.option.series[0].data[0]);
            this.option.series[0].data[0].value = response.data[0];
            this.option.series[0].data[1].value = response.data[1];
            this.option.series[0].data[2].value = response.data[2];
            this.option.series[0].data[3].value = response.data[3];
            this.option.series[0].data[4].value = response.data[4];
          }

      )
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