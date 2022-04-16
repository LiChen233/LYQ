<template>
  <page-header-wrapper>
    <a-card :bordered="false" style="margin-bottom: 20px;">
      <h1>名言名句</h1>
      <div style="font-weight: bold">{{ juzi[index] }}</div>
    </a-card>
    <a-card :bordered="false" style="margin-bottom: 20px;">
      <h1>今日天气</h1>
      <div style="width: 300px;display: flex;align-items: center;">
        <a-input v-model="city" placeholder="输入城市"></a-input>
        <a-button type="primary" style="margin-left: 20px;" @click="getWeather">查询</a-button>
      </div>
      <div>
        <div class="weather">
          <div>
            <p>天气：{{ weather.realtime.info }}</p>
            <p>风向：{{ weather.realtime.direct }}</p>
          </div>
          <div>
            <p>温度：{{ weather.realtime.temperature }}℃</p>
            <p>风力：{{ weather.realtime.power }}</p>
          </div>
          <div>
            <p>湿度：{{ weather.realtime.humidity }}</p>
            <p>空气质量指数：{{ weather.realtime.aqi }}</p>
          </div>
        </div>
        <h1>未来天气</h1>
        <div class="future">
          <div style="display: flex;align-items: center" v-for="(item,index) in weather.future">
            <div>
              <p>日期：{{ item.date }}</p>
              <p>天气：{{ item.weather }}</p>
              <p>温度：{{ item.temperature }}</p>
              <p>风向：{{ item.direct }}</p>
            </div>
            <a-divider style="height: 90%;margin: 0 30px" type="vertical" v-if="index!=weather.future.length-1"/>
          </div>
        </div>
      </div>
    </a-card>
    <a-card :bordered="false">
      <h1>今日作文鉴赏：{{ zuowen[index2].title }}</h1>
      <div>
        <div style="text-align: left;" v-html="zuowen[index2].content"></div>
      </div>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import reqApi from '@/utils/reqApi'
import {baseUrl} from "@/api/system/user";
import juzi from "@/views/dashboard/juzi";
import zuowen from "@/views/dashboard/zuowen";

export default {
  name: 'Workplace1',
  data() {
    return {
      juzi: juzi,
      index: 0,
      zuowen:zuowen,
      index2:0,
      city: '海宁',
      weather: {
        "city": "海宁",
        "realtime": {
          "temperature": "10",
          "humidity": "97",
          "info": "阴",
          "wid": "02",
          "direct": "东风",
          "power": "1级",
          "aqi": "76"
        },
        "future": [
          {
            "date": "2022-04-17",
            "temperature": "11/18℃",
            "weather": "阴转小雨",
            "wid": {
              "day": "02",
              "night": "07"
            },
            "direct": "东风转东南风"
          },
          {
            "date": "2022-04-18",
            "temperature": "10/19℃",
            "weather": "阴转晴",
            "wid": {
              "day": "02",
              "night": "00"
            },
            "direct": "西北风转持续无风向"
          },
          {
            "date": "2022-04-19",
            "temperature": "11/22℃",
            "weather": "多云转阴",
            "wid": {
              "day": "01",
              "night": "02"
            },
            "direct": "东风转东南风"
          },
          {
            "date": "2022-04-20",
            "temperature": "15/22℃",
            "weather": "阴",
            "wid": {
              "day": "02",
              "night": "02"
            },
            "direct": "东南风转南风"
          },
          {
            "date": "2022-04-21",
            "temperature": "15/24℃",
            "weather": "多云转晴",
            "wid": {
              "day": "01",
              "night": "00"
            },
            "direct": "东南风"
          }
        ]
      }
    }
  },
  created() {
    // this.getWeather()
    this.index = Math.floor(Math.random() * this.juzi.length);
    this.index2 = Math.floor(Math.random() * this.zuowen.length);
  },
  methods: {
    getWeather() {
      reqApi({
        url: baseUrl.means.getWeather,
        params: {
          city: this.city
        }
      }).then(res => {
        this.weather = res.data
        this.$message.success("查询成功，未雨绸缪是一种好习惯")
      })
    }
  }
}
</script>

<style lang="less" scoped>
.weather {
  margin-top: 20px;
  display: flex;

  div {
    margin-right: 40px;
  }
}

.future {
  display: flex;
}
</style>
