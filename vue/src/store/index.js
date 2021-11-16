import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: '',
        identity:'',
        //包括用户身份，用户信息
        userInfo: {
            name: '',
            phone: '',
            idcard: '',
            ages: '',
        },
        router:''
    },
    mutations: {
        setRouter(state,value){
            console.log('new router state '+value)
            state.router=value
        },
        SET_USER(state,user){
            console.log("用户信息"+user);
            state.userInfo=user;
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        SET_TOKEN: (state, token) => {
            state.token = token;
            localStorage.setItem("token", token)
        },
        SET_IDENTITY:(state,identity ) =>{
            state.identity = identity;
            localStorage.setItem("identity", identity)
        } ,
        REMOVE_INFO: (state) => {
            state.token = ""
            state.userInfo = {}
            localStorage.setItem("token", '')
            sessionStorage.setItem("userInfo", JSON.stringify(''))
        },
        remove_user:(state) =>{
            state.userInfo={}
            sessionStorage.setItem("userInfo",JSON.stringify(''))
        }
    },
    getters: {
        getToken : state => {
            return state.token
        },
        getIndentity:state => {
            return this.state.identity
        },
        getUser:state => {
            return this.state.user;
        }
    },
    actions: {},
    modules: {}
})
