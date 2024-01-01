<script>
import {ElNotification} from "element-plus";
import axios from 'axios';
import router from "@/router";

export default {
  data() {
    return {

      regForm: {
        username: '',
        password: '',
        department: '',

      },

      departments: [
        {
          "id": 1,
          "label": "市场部",
          "value": "1"
        },
        {
          "id": 2,
          "label": "HR部",
          "value": "2"
        },{
          "id": 3,
          "label": "IT部",
          "value": "3"
        }
      ]
    };
  },
  methods: {
    validateUsername(rule, value, callback) {

      if (value.length >= 5 && value.length <= 10) {
        callback();

      } else {
        callback(new Error('账户名长度必须为5-10个字符'));
      }

    },


    validatePassword(rule, value, callback) {

      if (value.length >= 5 && value.length <= 10) {
        callback();

      } else {
        callback(new Error('账户密码长度必须为5-10个字符'));
      }

    },


    register() {
      this.$refs.regForm.validate((valid) => {
        if (valid) {
          console.log(this.regForm.username,this.regForm.password,this.regForm.department)
          

          //Send Post Request

          const postData = {
            username : this.regForm.username,
            userpassword : this.regForm.password,
            departmentname: this.regForm.department
          }

          const postDataJson = JSON.stringify(postData)

          console.log(postData);

          axios.post('http://localhost:8080/api/register', postDataJson,{
            headers:{
              'Content-Type': 'application/json',
            }
          })
          .then(res => {

              if(res.data['code'] > 0 )
              {
                const notificationOptions = {
                  title: '错误',
                  message: res.data['message'],
                  type: 'error',
                };
                ElNotification(notificationOptions);
                
              }else
              {
                const notificationOptions = {
                  title: '成功',
                  message: res.data['message'] + "请你现在登录",
                  type: 'success',
                };
                ElNotification(notificationOptions);

                router.push('/login');
              }
              
          })


          
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
  <el-row justify="center" align="middle" id="el-row">
    <el-col :md="{span:'6'}" :sm="{span:'12'}" id="el-col">
      <el-form ref="regForm" :model="regForm" label-width="80px" rules="rules" style="padding: 20px">
        <h3>注册</h3>

        <hr>
        <el-form-item label="账户名" prop="username"
                      :rules="[{ required: true, message: '请输入账户名', trigger: 'blur' }, {validator: validateUsername , trigger: 'blur'}]">
          <el-input v-model="regForm.username" type="text" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="department" :rules="[{ required: true, message: '请输入部门', trigger: 'blur' }]" >
          <el-select v-model="regForm.department" placeholder="选择部门">
            <el-option
                v-for="item in departments"
                :key="item.value"
                :label="item.label"
                :value="item.label"
            >
              <span style="float: left">{{ item.label }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="密码" prop="password"
                      :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }, {validator: validatePassword , trigger: 'blur'}]">
          <el-input v-model="regForm.password" type="password" autocomplete="off"></el-input>
        </el-form-item>
        <el-row>
          <el-button class="btn" type="primary" @click="register">注册</el-button>
          <p>有账户，请你 <router-link to="/login"><a href="">登录</a> </router-link></p>
        </el-row>
      </el-form>
    </el-col>
  </el-row>
</template>



<style scoped>
input {
  border: 1px solid lightblue;
}

select {
  border: 1px solid lightblue;
}

#el-row {
  height: 100vh;
}

#el-col {
  box-shadow: 10px 10px 10px 10px lightblue;
  padding: 10px;
  border: 1px solid black
}

.btn
{
  margin: 3px;
}

p{
  margin: 5px 5px;
}
</style>