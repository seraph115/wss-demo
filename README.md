# WSS-DEMO

## Demo Database Init

### Create Table SQL 
```sql
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    company VARCHAR(100)
);
```

### the fake Customer Data Insert SQL
```sql
INSERT INTO customer (name, phone, company) VALUES
('张伟', '13800138000', '华为技术有限公司'),
('王芳', '13800138001', '阿里巴巴集团控股有限公司'),
('李强', '13800138002', '腾讯控股有限公司'),
('刘洋', '13800138003', '百度在线网络技术有限公司'),
('陈杰', '13800138004', '京东集团股份有限公司'),
('杨静', '13800138005', '小米科技有限责任公司'),
('赵磊', '13800138006', '美团点评'),
('孙娜', '13800138007', '字节跳动有限公司'),
('周鑫', '13800138008', '拼多多'),
('吴彤', '13800138009', '网易公司');
```

## Calling API to trigger data push via Postman tools

### Enter the 'Customer ID' number in the Body
```
[http://localhost:8080/api/customer/push](http://localhost:8080/api/customer/push)
```

### WebSocket Client HTML page
```
http://localhost:8080/index.html
```
