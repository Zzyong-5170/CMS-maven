-- 添加密码字段
alter table sys_users add sys_user_password varchar(50) not null;
alter table sys_users drop column sys_user_password
alter table sys_users add sys_users_password varchar(50) not null;
-- 添加数据
insert into sys_users (id, sys_users_id, sys_users_name, sys_users_password) values ('1', '1', 'test', 'test');
-- 修改字段类型
alter table sys_users change column sys_users_password sys_users_password varchar(200);
-- 更新密码
update sys_users set sys_users_password = '$shiro1$SHA-512$1$$7iaw3Ur350mqGo7jwQrpkj9hiYB3Lkc/iBml1JQODbJ6wYX4oOHV+E+IvIh/1nsUNzLDBMxfqa2Ob1f1ACio/w==';