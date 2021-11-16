import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import axios from 'axios'
import '@mdi/font/css/materialdesignicons.css'


Vue.use(Vuetify)
Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
  vuetify:new Vuetify,
  router,
  store,
  icons: {
    iconfont: 'fa',
    values: {
      cancel: 'fas fa-ban',
      menu: 'fas fa-ellipsis-v',
    },
  },
  render: h => h(App)
}).$mount('#app')
