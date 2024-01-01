<script>
import SlidebarComponent from "@/components/SlidebarComponent.vue";
import {jwtDecode} from 'jwt-decode';
import axios from "axios";
import moment from 'moment';

export default {
  name: "ReciviedView",
  components: {SlidebarComponent},
  data() {
    return {
      tableData: [],
      loading: false,
    }
  },
  mounted()
  {
    const gettoken = localStorage.getItem("token");

    const decodedToken = jwtDecode(gettoken);

    const userId = decodedToken.claims.id;

    this.userId = userId;
    console.log("UserID is " +  this.userId);


    const postData = {
        userid: this.userId,
      }

      axios.post('api/receivedMessage', postData,{
            headers: {
              'Content-Type': 'application/json',
              'Authorization': localStorage.getItem("token")
            }
      }).then(res =>{

        if(res.data['code'] == 0 )
        {

          console.log(res.data.data);

          this.tableData = res.data.data;

        }
        else
        {
          const notificationOptions = {
                    title: '失败',
                    message: '操作失败',
                    type: 'error',
                  };

          ElNotification(notificationOptions)
        }})
  },
  methods: {
    timeAgo(dateTime) {
      return moment(dateTime).fromNow();
    }
  },
}
</script>

<template>

  <el-row :gutter="20">
    <el-col :span="3" style="height: 100vh">
      <SlidebarComponent active-index="2"/>
    </el-col>
    <el-col :span="20">
      <div class="main">
        <div class="shadow-box">
          <h1>收件消息</h1>
          <hr>
          <table class="table table-bordered" >
            <thead>
            <tr>
              <th scope="col">收件人</th>
              <th scope="col">部门</th>
              <th scope="col">信息</th>
              <th scope="col">时间</th>
              <!-- <th scope="col">操作</th> -->
            </tr>
            </thead>
            <tbody>
            <!-- Example row, you can add more rows dynamically -->
            <tr v-for="items in tableData" >
              <td><span class="badge bg-primary">{{items.usersinfo.username}}</span></td>
              <td><span class="badge bg-info">{{items.usersinfo.departmentname}}</span></td>
              <td>{{items.message}}</td>
              <td>{{ timeAgo(items.createat) }}</td>
              <!-- <td>
                <button type="button" class="btn btn-primary btn-sm  ">阅读</button>
                <button type="button" class="btn btn-danger btn-sm " style="margin-left: 2px">删除</button>
              </td> -->
            </tr>
            <!-- Add more rows as needed -->
            </tbody>
          </table>
        </div>
      </div>
    </el-col>
  </el-row>

</template>

<style scoped>
.shadow-box {
  box-shadow: 10px 10px 10px 10px lightblue;
  padding: 20px;
}

.el-row {

  margin-right: 0px !important;
}

</style>