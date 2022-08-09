const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    proxy: {
      '/':{
        "target":'http://i7a506.p.ssafy.io:8080',
        "pathRewrite": {'^/':''},
        "changeOrigin":true,
      }
    }
  }
}