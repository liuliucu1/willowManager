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
      <v-form
          ref="form"
          v-model="valid"
          lazy-validation
      >
        <v-text-field
            v-model="name"
            :counter="10"
            :rules="nameRules"
            label="课程名"
            required
        ></v-text-field>

        <v-select
            v-model="e1"
            :items="kinds"
            label="课程类型"
            required
            @change="setKind"
            chips
        >
        </v-select>

        <v-select
            v-model="e2"
            :items="locations"
            :rules="[v => !!v || '老师，请输入开课地点~']"
            label="开课地点"
            chips
            @change="setLocation"
            required
        ></v-select>


        <v-container fluid>
          <v-row align="center">
            <v-col
                class="d-flex"
                cols="12"
                sm="6"
            >
              <v-select
                  v-model="e3"
                  :items="start_weeks"
                  filled
                  label="开课第一周"
                  :rules="[v => !!v || '请输入课程时间~']"
                  dense
                  @change="setWeek1"
                  chips
                  required
              ></v-select>
            </v-col>

            <v-col
                class="d-flex"
                cols="12"
                sm="6"
            >
              <v-select
                  v-model="e4"
                  :items="end_weeks"
                  filled
                  @change="setWeek2"
                  label="开课最后周"
                  :rules="[v => !!v || '请输入课程时间~']"
                  dense
                  chips
                  required
              ></v-select>
            </v-col>

            <v-col
                class="d-flex"
                cols="12"
                sm="6"
            >
              <v-select
                  v-model="e5"
                  :items="time_days"
                  label="开课星期"
                  @change="setDay"
                  :rules="[v => !!v || '例：开课星期是每周六~']"
                  dense
                  chips
                  solo
                  required
              ></v-select>
            </v-col>

            <v-col
                class="d-flex"
                cols="12"
                sm="6"
            >
              <v-select
                  v-model="e6"
                  :items="items"
                  label="开设课次"
                  :rules="[v => !!v || '请输入具体是第几节课~']"
                  dense
                  @change="setDetail"
                  solo
                  required
                  chips
              ></v-select>
            </v-col>
          </v-row>
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
                  :rules="[v => !!v || '请输入开课学期~']"
                  dense
                  solo
                  required
                  chips
              ></v-select>
            </v-col>
            <v-col
                class="d-flex"
                cols="12"
                sm="6"
            >
              <v-select
                  v-model="e8"
                  :items="fees"
                  label="学分"
                  @change="setFee"
                  :rules="[v => !!v || '请输入学分~']"
                  dense
                  chips
                  solo
                  required
              ></v-select>
            </v-col>
          </v-row>
        </v-container>
        <v-text-field
            label="备注"
            required
        ></v-text-field>
        <v-checkbox
            v-model="checkbox"
            :rules="[v => !!v || '请先确认信息']"
            label="检查信息，确认无误后提交"
            required
        ></v-checkbox>
        <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="pass"
        >
          提交
        </v-btn>

        <v-btn
            color="error"
            class="mr-4"
            @click="reset"
        >
          重新填写表单
        </v-btn>

      </v-form>
    </v-main>

  </v-app>
</template>

<script>
import querystring from "querystring";
import sweet from "sweetalert2";



export default {
  name: "TeacherAdd",

  data: () => ({
    name: '',
    remark: '',
    myCourse: {
      day: '',
      name: '',
      kind: '',
      detail: '',
      location: '',
      semester: '',
      fee: '',
      week1: '',
      week2: '',
      remark: ''
    },

    e1: [],
    e2: [], e3: [], e4: [], e5: [], e6: [], e7: [], e8: [],
    drawer: true,
    valid: true,
    checkbox1: true,
    checkbox2: false,
    nameRules: [
      v => !!v || '请输入课程名',
      v => (v && v.length <= 20) || '课程名须在20个字符内',
    ],
    select: null,
    kinds: [
      '必修课程',
      '国学人文',
      '艺术生活',
      '科学素养',
      '实践创新',
    ],
    locations: [
      '中心校区',
      '洪家楼校区',
      '软件园校区',
      '千佛山校区',
      '兴隆山校区',
      '趵突泉校区',
    ],
    start_weeks: [
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8'
    ],
    end_weeks: [
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8'
    ],
    time_days: [
      '星期一',
      '星期二',
      '星期三',
      '星期四',
      '星期五',
      '星期六',
      '星期日',
    ],

    semesters: [
      '2021-2',
      '2022-1',
      '2022-2',
    ],
    fees: [
      '1', '1.5', '2', '2.5', '3', '3.5', '4'],
    items: [
      '1',
      '2', '3', '4', '5',
      '1-2', '3-4'
    ],
    checkbox: false,
    rules: {
      required: value => !!value || '不能为空',
    }
  }),

  methods: {
    pass() {
      if (this.myCourse.week1 === '' || this.myCourse.week2 === '' || this.myCourse.kind === '' || this.myCourse.fee === '' || this.myCourse.day === '' || this.myCourse.semester === '' || this.myCourse.location === '') {
        sweet.fire({
          icon: "error",
          text: "请检查信息是否完整"
        })
      } else if (this.time_start > this.time_end) {
        sweet.fire({
          icon: "error",
          text: "请检查开课时间"
        })
      } else {
        this.validate();
      }

    },
    validate() {
      this.$refs.form.validate()
      this.myCourse.remark = this.remark;
      this.myCourse.name = this.name;
      console.log(this.name);
      console.log(this.myCourse.name);
      if (this.valid) {
        this.$axios.post('http://localhost:8800/addCourse', querystring.stringify({
          name: this.myCourse.name,
          time_start: this.myCourse.week1,
          time_end: this.myCourse.week2,
          fee: this.myCourse.fee,
          location: this.myCourse.location,
          week: this.myCourse.detail,
          semester: this.myCourse.semester,
          remark: this.myCourse.remark,
          time_class: this.myCourse.day,
          kind: this.myCourse.kind,
          token: localStorage.getItem("token")
        })).then(res => {
          console.log(res);
          if (res.data.code===0){
            sweet.fire({
              icon: "error",
              text: "课程时间冲突"
            })
          }else{
            sweet.fire({
              icon: "success",
              text: "添加成功"
            })
          }
        })
      }
    },
    reset() {
      this.$refs.form.reset()
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
    setKind(value_type) {
      this.myCourse.kind = value_type;
    },
    setLocation(value_type) {
      this.myCourse.location = value_type;
    },
    setFee(value_type) {
      this.myCourse.fee = value_type;
    },
    setDetail(value_type) {
      this.myCourse.detail = value_type;
    },
    setWeek1(value_type) {
      this.myCourse.week1 = value_type;
    },
    setWeek2(value_type) {
      this.myCourse.week2 = value_type;
    },
    setDay(value_type) {
      this.myCourse.day = value_type;
    },
    setSemester(value_type) {
      this.myCourse.semester = value_type;
    },

  }
}
</script>

<style scoped>

</style>