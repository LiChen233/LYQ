<template>
  <a-config-provider :locale="locale">
    <div id="app">
      <router-view/>
    </div>
  </a-config-provider>
</template>

<script>
import { domTitle, setDocumentTitle } from '@/utils/domUtil'
import { i18nRender } from '@/locales'
import reqApi from "@/utils/reqApi";
import {baseUrl} from "@/api/system/user";

export default {
  data () {
    return {
    }
  },
  created() {
    //通知
    reqApi({
      url: baseUrl.notice.getNotice
    }).then(res => {
      let icon = <a-icon type="smile" style="color: #52c41a"/>;
      for (let key in res.data) {
        let notice = res.data[key]
        icon.componentOptions.propsData.type = notice.icon
        this.$notification.open({
          message: notice.title,
          icon: icon,
          description: notice.remark,
          duration: 0,
        })
      }
    })
  },
  computed: {
    locale () {
      // 只是为了切换语言时，更新标题
      const { title } = this.$route.meta
      title && (setDocumentTitle(`${i18nRender(title)} - ${domTitle}`))

      return this.$i18n.getLocaleMessage(this.$store.getters.lang).antLocale
    }
  }
}
</script>
