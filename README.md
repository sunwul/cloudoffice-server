# CloudOffice服务端

## 服务端架构
> 1. SpringBoot(整体框架)
> 2. Mybatis-Plus(单表CURD免写SQL,提高开发效率)
> 3. AutoGenerator(Mybatis-Plus提供的代码生成器) 
> 4. Swagger2(API接口文档,UI使用的第三方swagger-bootstrap-ui)
> 5. SpringSecurity(安全)
> 6. JWT(token)
> 7. Kaptcha(google验证码生成模块)
> 8. Redis(大数据的缓存,减轻数据库读写压力)
> 9. RabbitMQ(消息的可靠性与幂等性处理)
> 10. EasyPOI(导入导出)
> 11. Mail(邮件发送)
> 12. WebSocket(在线聊天)
> 13. FastDFS(分布式文件服务器)

## 待完善功能
> 有关联数据的操作统一`throw SQLIntegrityConstraintViolationException`
> 1. 删除角色时,需要先查询是否有admin关联此角色(有则不允许删除),
>    且删除时需要同时删除角色菜单关联表中的关联数据
> 2. 删除职位时,需要先查询是否有员工关联此职位
> 3. 删除职称时,需要先查询是否有员工关联此职称
> 4. 删除部门时,需要考虑是否有员工属于此部门(最下级部门)
>