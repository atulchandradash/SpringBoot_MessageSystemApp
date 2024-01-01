<script>
import {ElNotification} from "element-plus";
import axios from 'axios';
import router from "@/router";



export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    validateUsername(rule,value,callback){

      if (value.length >= 5 && value.length <= 10)
      {
        callback();

      }else
      {
        callback(new Error('账户名长度必须为5-10个字符'));
      }

    },


    validatePassword(rule,value,callback){

      if (value.length >= 5 && value.length <= 10)
      {
        callback();

      }else
      {
        callback(new Error('账户密码长度必须为5-10个字符'));
      }

    },



    login() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {

          const postData = {
            username: this.loginForm.username,
            userpassword: this.loginForm.password
          }

          const postDataJson = JSON.stringify(postData);
          
          axios.post('http://localhost:8080/api/login', postData,{
            headers: {
              'Content-Type': 'application/json',
            }
          }).then(res => {

                if(res.data['data'] != null)
                {
                  console.log(res.data['data']);

                  

                  const token = localStorage.setItem("token",res.data['data'] );

                  const notificationOptions = {
                    title: '成功',
                    message: '登录成功',
                    type: 'success',
                  };

                  ElNotification(notificationOptions);

                  router.push('/')
                }

                else
                {
                  const notificationOptions = {
                    title: '错误',
                    message: res.data['message'],
                    type: 'error',
                  };

                  ElNotification(notificationOptions);
                }
              

          }).catch(error=>{
              console.log(error)
          });
          

        } else {
          console.log('Login failed');
          const notificationOptions = {
            title: '错误',
            message: '请填写表格并确保正确。',
            type: 'error',
          };

          ElNotification(notificationOptions);
        }
      });
    },
  },
};
</script>

<template>
  <el-row justify="center" align="middle" id="el-row" >
    <el-col  :md="{span:'6'}" :sm="{span:'12'}"   id="el-col">
      <el-form ref="loginForm" :model="loginForm" label-width="80px" rules="rules" style="padding: 20px">
        <h3>登录</h3>

        <hr>
        <el-form-item label="账户名" prop="username" :rules="[{ required: true, message: '请输入账户名', trigger: 'blur' }, {validator: validateUsername , trigger: 'blur'}]">
          <el-input v-model="loginForm.username" type="text" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }, {validator: validatePassword , trigger: 'blur'}]">
          <el-input v-model="loginForm.password" type="password" autocomplete="off"></el-input>
        </el-form-item>
        <el-row>
          <el-button class="btn" type="primary" @click="login">登录</el-button>
          <p>没有账户，请你 <router-link to="/register"><a href="">注册</a> </router-link></p>
        </el-row>
      </el-form>
    </el-col>
  </el-row>
</template>

<style scoped>
#el-row
{
  height: 100vh;
}

#el-col
{
  box-shadow: 10px 10px 10px 10px lightblue; padding: 10px;border: 1px solid black
}

.btn
{
  margin: 3px;
}

p{
  margin: 5px 5px;
}
</style>