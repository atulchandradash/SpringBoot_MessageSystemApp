<script>

import SlidebarComponent from "@/components/SlidebarComponent.vue";
import axios from "axios";
import {ElNotification} from "element-plus";
import {jwtDecode} from 'jwt-decode';

export default {
  name: "CreateMsgView",
  components: {SlidebarComponent },
  data(){
    return {

      createMsg: {
        username: '',
        msg: '',
        department: '',
        person: ''
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
      ],
      persons: [],
      userId: '',



    };
  }, 
  
  mounted() {

    const gettoken = localStorage.getItem("token");

    const decodedToken = jwtDecode(gettoken);

    const userId = decodedToken.claims.id;

    this.userId = userId;
    console.log("UserID " + userId);


    },
  
  methods: {
    validateUsername(rule, value, callback) {

      if (value.length >= 5 && value.length <= 10) {
        callback();

      } else {
        callback(new Error('账户名长度必须为5-10个字符'));
      }

    },
    login() {
      this.$refs.createMsg.validate((valid) => {
        if (valid) {
           console.log(this.createMsg.person);
            console.log(this.createMsg.msg);

          this.sentMsg(this.createMsg.person,this.createMsg.msg,this.userId)

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

    departmentPerson()
    {
       const department = this.createMsg.department;

       const postData = {
           departmentname: department,

          }
          const postDataJson = JSON.stringify(postData);

          console.log("deaprtment :"+ postDataJson);


       axios.post("/api/department/",postData, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem("token")
        }
      }).then(res => {
        
        this.persons = res.data.data;

        console.log(this.persons);

        


      }).catch(error => {
        console.error(error);
      });

       
    },


    //create sentMsg

    sentMsg(senderid,message,userid)
    {

      const postData = {
        senderid: senderid,
        message: message,
        userid:userid
      }



      axios.post('api/message', postData,{
            headers: {
              'Content-Type': 'application/json',
              'Authorization': localStorage.getItem("token")
            }
      }).then(res =>{

        if(res.data['code'] == 0 )
        {
          const notificationOptions = {
                    title: '成功',
                    message: '登录成功',
                    type: 'success',
                  };

          ElNotification(notificationOptions)
        }
        else
        {
          const notificationOptions = {
                    title: '失败',
                    message: '操作失败',
                    type: 'error',
                  };

          ElNotification(notificationOptions)
        }

      })
    }




  },
}
</script>

<template>

  <el-row :gutter="20">
      <el-col :span="3" style="height: 100vh">
        <SlidebarComponent active-index="1" />
      </el-col>

    <el-col :span="20">
      <div class="main">
        <div class="shadow-box">
          <h1>创建消息</h1>
          <hr>
          <el-form ref="createMsg" :model="createMsg" label-width="80px" rules="rules" style="padding: 20px">
            <el-form-item label="部门" prop="department" :rules="[{ required: true, message: '请输入部门', trigger: 'blur' }]" >
              <el-select v-model="createMsg.department" @change="departmentPerson" placeholder="选择部门">
                <el-option
                    v-for="item in departments"
                    :key="item.id"
                    :label="item.label"
                    :value="item.label"
                >
                  <span style="float: left">{{ item.label }}</span>
                </el-option>
              </el-select>
            </el-form-item>

            
            <el-form-item label="人" prop="person" :rules="[{ required: true, message: '请输入人', trigger: 'blur' }]" >
              <el-select v-model="createMsg.person"   placeholder="选择人">
                <el-option
                    v-for="item in persons"
                    :key="item.userid"
                    :label="item.username"
                    :value="item.userid"
                    :disabled="item.userid === this.userId"
                >
                  <span  style="float: left"  >{{ item.username }}</span>
                </el-option>
              </el-select>
            </el-form-item>


            <el-form-item label="信息" prop="msg"
                          :rules="[{ required: true, message: '请输入信息', trigger: 'blur' }, {validator: validatePassword , trigger: 'blur'}]">
              <el-input
                  v-model="createMsg.msg"
                  :rows="5"
                  type="textarea"

              />
            </el-form-item>
            <el-button class="btn" type="primary" @click="login">提交</el-button>
          </el-form>
        </div>
      </div>
    </el-col>

  </el-row>




</template>

<style scoped>

.main
{
  padding: 30px;
  line-height: 50px;
}
.shadow-box
{
  box-shadow: 10px 10px 10px 10px lightblue;
  padding: 20px;
}
select
{
  margin-bottom: 3px ;
  border: 1px solid lightblue;
}

textarea
{
  border: 1px solid lightblue;
}
.el-row
{

  margin-right: 0px !important;
}

</style>