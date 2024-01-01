<script >
import router from '@/router';
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue';
import {defineComponent} from 'vue';

export default defineComponent({
  components: {
    Document,
    IconMenu,
    Location,
    Setting,
  },
  props: {
      activeIndex: '',
  },
  data()
  {
    return{
      activeIndex: this.activeIndex,
    }
  },

  methods: {
    logout()
    {
      localStorage.removeItem('token');
      router.push('/login')
    }
  },
});
</script>

<template>
  <el-menu
      :default-active="activeIndex"
      @open="handleOpen"
      @close="handleClose"
      active-text-color="#ffd04b"
      background-color="black"
      text-color="#fff"
      style="height: 100%;"
  >
    <h5 style="color: white;text-align: center;padding: 10px;">留言版系统</h5>
    <hr style="border-bottom: 3px solid white">
    <router-link to="/">
      <el-menu-item index="1">
        <el-icon>
          <icon-menu/>
        </el-icon>
        <span>创建消息</span>
      </el-menu-item>
    </router-link>
    <router-link to="recMsg">
      <el-menu-item index="2">
        <el-icon>
          <document/>
        </el-icon>
        <span>接收信息</span>
      </el-menu-item>
    </router-link>
    <router-link to="/sentMsg">
      <el-menu-item index="3">
        <el-icon>
          <setting/>
        </el-icon>
        <span>已发送消息</span>
      </el-menu-item>
    </router-link>
    <hr style="border-bottom: 3px solid white">
    <el-menu-item index="4">
      <el-icon>
        <setting/>
      </el-icon>
      <span @click="logout">推出</span>
    </el-menu-item>
  </el-menu>

</template>


<!--<a href="/" :class="{active :  activeLink === 'createMsg'} ">创建消息</a>-->
<!--<a href="/recMsg" :class="{active : activeLink === 'recMsg'}" >接收信息</a>-->
<!--<a href="/sentMsg" :class="{active : activeLink === 'sentMsg'}">已发送消息</a>-->

<style scoped>
.active {
  background: lightgray;
  color: blue !important;
}


</style>