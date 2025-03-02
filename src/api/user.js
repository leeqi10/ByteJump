import request from '@/utils/request'

export const userApi = {
  // 用户注册
  register(username, password) {
    return request({
      url: '/douyin/user/register',
      method: 'post',
      data: {
        username,
        password
      }
    })
  },

  // 用户登录
  login(username, password) {
    return request({
      url: '/douyin/user/login',
      method: 'post',
      data: {
        username,
        password
      }
    })
  },

  // 获取用户信息
  getUserInfo(userId, token) {
    return request({
      url: '/douyin/user/',
      method: 'get',
      params: {
        user_id: userId,
        token
      }
    })
  },

  // 视频点赞
  likeVideo(token, videoId, actionType) {
    return request({
      url: '/douyin/favorite/action/',
      method: 'post',
      data: {
        token,
        video_id: videoId,
        action_type: actionType
      }
    })
  },

  // 获取用户喜欢列表
  getLikeList(token, userId) {
    return request({
      url: '/douyin/favorite/list/',
      method: 'get',
      params: {
        token,
        user_id: userId
      }
    })
  }
} 