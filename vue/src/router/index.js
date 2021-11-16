import Vue from 'vue'
import VueRouter from 'vue-router'
import Info from "@/views/Student/Info.vue";
import Course from "@/views/Student/Course.vue";
import Login from "@/views/Login.vue";
import SelectCourse from "@/views/Student/SelectCourse.vue";
import TeacherCourse from "@/views/Teacher/TeacherCourse.vue";
import MyTable from "@/views/Student/MyTable";
import MyScore from "@/views/Student/MyScore";
import MyCourse from "@/views/Teacher/MyCourse";
import TeacherInfo from "@/views/Teacher/TeacherInfo";
import TeacherAdd from "@/views/Teacher/TeacherAdd";
import StudentScore from "@/views/Teacher/StudentScore";
import chooseClass from "@/views/chooseClass"


const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
Vue.use(VueRouter)
const routes = [
    {
        path:'/',
        redirect:{
            name:'login'
        }
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/student',
        component: Course
    },
    {
        path: '/student/info',
        // name: 'info',
        meta: {
            requireAuth: true
        },
        component: Info
    },
    {
        path: '/student/info/fixInfo',
        // name: 'info',
        meta: {
            requireAuth: true
        },
        component: Info
    },
    {
        path: '/student/selectCourse',
        // name: 'selectCourse',
        meta: {
            requireAuth: true
        },
        component: SelectCourse
    },
    {
      path: '/student/mytable',
      // name: 'mytable',
      meta: {
          requireAuth: true
      } ,
      component: MyTable
    },
    {
        path: '/student/selectCourse/getCourse',
        // name: 'selectCourse',
        meta: {
            requireAuth: true
        },
        component: SelectCourse
    },
    {
        path: '/student/selectCourse/chooseCourse',
        // name: 'selectCourse',
        meta: {
            requireAuth: true
        },
        component: SelectCourse
    },
    {
        path: '/student/selectCourse/quit',
        // name: 'selectCourse',
        meta: {
            requireAuth: true
        },
        component: SelectCourse
    },
    {
        path: '/student/lookScore',
        // name: 'score',
        meta: {
            requireAuth: true
        },
        component: MyScore
    },
    {
        path: '/teacher',
        // name: 'TeacherCourse',
        meta: {
            requireAuth: true
        },
        component: TeacherCourse
    },
    {
        path: '/teacher/info',
        // name: 'TeacherInfo',
        meta: {
            requireAuth: true
        },
        component: TeacherInfo
    },
    {
        path: '/teacher/addCourse',
        // name: 'TeacherCourse',
        meta: {
            requireAuth: true
        },
        component: TeacherAdd
    },
    {
        path: '/teacher/getMyC',
        // name: 'TeacherCourse',
        meta: {
            requireAuth: true
        },
        component: MyCourse
    },
    {
        path: '/teacher/MyCourse',
        // name: 'TeacherCourse',
        meta: {
            requireAuth: true
        },
        component: MyCourse
    },
    {
        path: '/teacher/lookScore',
        // name: 'TeacherCourse',
        meta: {
            requireAuth: true
        },
        component: StudentScore
    },
    {
        path: '/teacher/delete',
        // name: 'TeacherCourse',
        meta: {
            requireAuth: true
        },
        component: TeacherCourse
    },
    {
        path:'/open',
        meta: {
            requireAuth: true
        },
        component: chooseClass
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
