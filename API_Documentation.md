# 森林管理系统 API 接口文档

## 基本信息

- **基础URL**: `http://localhost:8080/api`
- **认证方式**: JWT Bearer Token
- **内容类型**: `application/json`
- **字符编码**: `UTF-8`

## 认证说明

### JWT Token 使用方式
在请求头中添加：
```
Authorization: Bearer <your_jwt_token>
```

### 角色权限说明
- `ROLE_ADMIN`: 系统管理员，拥有所有权限
- `ROLE_OPER`: 资源管理员，拥有资源管理权限
- `ROLE_VIEW`: 普通用户，只有查看权限

## 1. 认证相关接口

### 1.1 用户登录
- **接口地址**: `POST /auth/login`
- **权限要求**: 无需认证
- **请求参数**:
```json
{
    "username": "admin",
    "password": "123456"
}
```
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9...",
        "user": {
            "userId": 1,
            "username": "admin",
            "fullName": "系统管理员",
            "roles": ["ROLE_ADMIN"]
        }
    }
}
```

### 1.2 获取当前用户信息
- **接口地址**: `GET /auth/current`
- **权限要求**: 需要认证
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "userId": 1,
        "username": "admin",
        "fullName": "系统管理员",
        "roles": ["ROLE_ADMIN"]
    }
}
```

### 1.3 用户登出
- **接口地址**: `POST /auth/logout`
- **权限要求**: 需要认证
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

## 2. 用户管理接口

### 2.1 修改用户状态
- **接口地址**: `POST /user/changeStatus`
- **权限要求**: `USER_STATUS_UPDATE` 或 `ROLE_ADMIN`
- **请求参数**:
```json
{
    "userId": 1,
    "status": 1
}
```
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

## 3. 角色权限管理接口

### 3.1 获取权限树
- **接口地址**: `GET /permission/tree`
- **权限要求**: `ROLE_ADMIN`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "permissionId": 1,
            "permissionName": "系统管理",
            "permissionCode": "SYSTEM_MANAGE",
            "parentId": null,
            "children": [
                {
                    "permissionId": 2,
                    "permissionName": "用户管理",
                    "permissionCode": "USER_MANAGE",
                    "parentId": 1
                }
            ]
        }
    ]
}
```

### 3.2 分配角色权限
- **接口地址**: `POST /role/assignPermission`
- **权限要求**: `ROLE_ADMIN`
- **请求参数**:
```json
{
    "roleId": 1,
    "permissionIds": [1, 2, 3, 4]
}
```
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

## 4. 林分管理接口

### 4.1 获取林分列表（分页）
- **接口地址**: `GET /stand/list`
- **权限要求**: `ROLE_ADMIN` 或 `ROLE_OPER`
- **请求参数**:
  - `standName`: 林分名称（可选，模糊查询）
  - `parcelId`: 地块ID（可选）
  - `aspect`: 坡向（可选）
  - `page`: 页码（默认0）
  - `size`: 每页大小（默认10）
  - `sort`: 排序字段（可选）

- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "content": [
            {
                "standId": 1,
                "standCode": "ST001",
                "standName": "松树林分",
                "parcelId": 1,
                "parcelName": "地块A",
                "area": 100.50,
                "aspect": "南",
                "slope": "15°",
                "soilType": "红壤",
                "hydrology": "良好",
                "remarks": "备注信息",
                "species": [
                    {
                        "standSpeciesId": 1,
                        "speciesId": 1,
                        "speciesName": "马尾松",
                        "speciesCode": "PMS",
                        "proportion": 80.0,
                        "age": 15,
                        "avgHeight": 12.5,
                        "avgDiameter": 18.0,
                        "density": 1200,
                        "volume": 150.0,
                        "canopy": "郁闭",
                        "originType": "人工林"
                    }
                ]
            }
        ],
        "totalElements": 100,
        "totalPages": 10,
        "size": 10,
        "number": 0
    }
}
```

### 4.2 获取所有林分（不分页）
- **接口地址**: `GET /stand/listAll`
- **权限要求**: `ROLE_VIEW`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "standId": 1,
            "standCode": "ST001",
            "standName": "松树林分",
            "parcelId": 1,
            "parcelName": "地块A",
            "area": 100.50,
            "aspect": "南",
            "slope": "15°",
            "soilType": "红壤",
            "hydrology": "良好",
            "remarks": "备注信息"
        }
    ]
}
```

### 4.3 获取林分详情
- **接口地址**: `GET /stand/{id}`
- **权限要求**: `STAND_READ`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "standId": 1,
        "standCode": "ST001",
        "standName": "松树林分",
        "parcelId": 1,
        "parcelName": "地块A",
        "area": 100.50,
        "aspect": "南",
        "slope": "15°",
        "soilType": "红壤",
        "hydrology": "良好",
        "remarks": "备注信息",
        "species": [...]
    }
}
```

### 4.4 创建林分
- **接口地址**: `POST /stand`
- **权限要求**: `STAND_CREATE`
- **请求参数**:
```json
{
    "standCode": "ST002",
    "standName": "杉木林分",
    "parcelId": 1,
    "area": 150.00,
    "aspect": "东",
    "slope": "20°",
    "soilType": "黄壤",
    "hydrology": "一般",
    "remarks": "新建林分"
}
```
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "standId": 2,
        "standCode": "ST002",
        "standName": "杉木林分",
        "parcelId": 1,
        "parcelName": "地块A",
        "area": 150.00,
        "aspect": "东",
        "slope": "20°",
        "soilType": "黄壤",
        "hydrology": "一般",
        "remarks": "新建林分"
    }
}
```

### 4.5 更新林分
- **接口地址**: `PUT /stand/{id}`
- **权限要求**: `STAND_UPDATE`
- **请求参数**: 同创建林分
- **响应示例**: 同创建林分

### 4.6 删除林分
- **接口地址**: `DELETE /stand/{id}`
- **权限要求**: `STAND_DELETE`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

## 5. 树种管理接口

### 5.1 获取树种列表（分页）
- **接口地址**: `GET /species/list`
- **权限要求**: `ROLE_ADMIN` 或 `ROLE_OPER`
- **请求参数**:
  - `speciesName`: 树种名称（可选，模糊查询）
  - `speciesCode`: 树种编码（可选，模糊查询）
  - `page`: 页码（默认0）
  - `size`: 每页大小（默认10）
  - `sort`: 排序字段（可选）

- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "content": [
            {
                "speciesId": 1,
                "speciesCode": "PMS",
                "speciesName": "马尾松",
                "familyName": "松科",
                "genusName": "松属",
                "latinName": "Pinus massoniana",
                "growthPattern": "常绿乔木",
                "economicValue": "用材林",
                "protectionValue": "水土保持",
                "remarks": "主要造林树种"
            }
        ],
        "totalElements": 50,
        "totalPages": 5,
        "size": 10,
        "number": 0
    }
}
```

### 5.2 获取所有树种（不分页）
- **接口地址**: `GET /species/listAll`
- **权限要求**: `ROLE_VIEW`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "speciesId": 1,
            "speciesCode": "PMS",
            "speciesName": "马尾松",
            "familyName": "松科",
            "genusName": "松属",
            "latinName": "Pinus massoniana",
            "growthPattern": "常绿乔木",
            "economicValue": "用材林",
            "protectionValue": "水土保持",
            "remarks": "主要造林树种"
        }
    ]
}
```

### 5.3 获取树种详情
- **接口地址**: `GET /species/{id}`
- **权限要求**: `SPECIES_READ`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "speciesId": 1,
        "speciesCode": "PMS",
        "speciesName": "马尾松",
        "familyName": "松科",
        "genusName": "松属",
        "latinName": "Pinus massoniana",
        "growthPattern": "常绿乔木",
        "economicValue": "用材林",
        "protectionValue": "水土保持",
        "remarks": "主要造林树种"
    }
}
```

### 5.4 创建树种
- **接口地址**: `POST /species`
- **权限要求**: `SPECIES_CREATE`
- **请求参数**:
```json
{
    "speciesCode": "CML",
    "speciesName": "杉木",
    "familyName": "杉科",
    "genusName": "杉属",
    "latinName": "Cunninghamia lanceolata",
    "growthPattern": "常绿乔木",
    "economicValue": "用材林",
    "protectionValue": "生态防护",
    "remarks": "速生用材树种"
}
```
- **响应示例**: 同获取树种详情

### 5.5 更新树种
- **接口地址**: `PUT /species/{id}`
- **权限要求**: `SPECIES_UPDATE`
- **请求参数**: 同创建树种
- **响应示例**: 同获取树种详情

### 5.6 删除树种
- **接口地址**: `DELETE /species/{id}`
- **权限要求**: `SPECIES_DELETE`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

## 6. 土地利用类型管理接口

### 6.1 获取土地利用类型列表（分页）
- **接口地址**: `GET /landUseType/list`
- **权限要求**: `ROLE_ADMIN` 或 `ROLE_OPER`
- **请求参数**:
  - `useTypeName`: 类型名称（可选，模糊查询）
  - `useTypeCode`: 类型编码（可选，模糊查询）
  - `page`: 页码（默认0）
  - `size`: 每页大小（默认10）
  - `sort`: 排序字段（可选）

- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "content": [
            {
                "useTypeId": 1,
                "useTypeCode": "FOREST",
                "useTypeName": "林地",
                "description": "用于林业生产的土地",
                "remarks": "主要土地利用类型"
            }
        ],
        "totalElements": 10,
        "totalPages": 1,
        "size": 10,
        "number": 0
    }
}
```

### 6.2 获取所有土地利用类型（不分页）
- **接口地址**: `GET /landUseType/listAll`
- **权限要求**: `ROLE_VIEW`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "useTypeId": 1,
            "useTypeCode": "FOREST",
            "useTypeName": "林地",
            "description": "用于林业生产的土地",
            "remarks": "主要土地利用类型"
        }
    ]
}
```

### 6.3 获取土地利用类型详情
- **接口地址**: `GET /landUseType/{id}`
- **权限要求**: `USETYPE_READ`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "useTypeId": 1,
        "useTypeCode": "FOREST",
        "useTypeName": "林地",
        "description": "用于林业生产的土地",
        "remarks": "主要土地利用类型"
    }
}
```

### 6.4 创建土地利用类型
- **接口地址**: `POST /landUseType`
- **权限要求**: `USETYPE_CREATE`
- **请求参数**:
```json
{
    "useTypeCode": "GRASSLAND",
    "useTypeName": "草地",
    "description": "用于畜牧业的土地",
    "remarks": "次要土地利用类型"
}
```
- **响应示例**: 同获取土地利用类型详情

### 6.5 更新土地利用类型
- **接口地址**: `PUT /landUseType/{id}`
- **权限要求**: `USETYPE_UPDATE`
- **请求参数**: 同创建土地利用类型
- **响应示例**: 同获取土地利用类型详情

### 6.6 删除土地利用类型
- **接口地址**: `DELETE /landUseType/{id}`
- **权限要求**: `USETYPE_DELETE`
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": null
}
```

## 7. 资源概览接口

### 7.1 获取资源概览
- **接口地址**: `GET /resource/overview`
- **权限要求**: 需要认证
- **请求参数**:
  - `landUseType`: 土地利用类型ID（可选）
  - `areaMin`: 最小面积（可选）
  - `areaMax`: 最大面积（可选）
  - `aspect`: 坡向（可选）

- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "totalArea": 1000.50,
        "totalStands": 50,
        "totalSpecies": 20,
        "landUseDistribution": [
            {
                "useTypeName": "林地",
                "area": 800.00,
                "percentage": 80.0
            },
            {
                "useTypeName": "草地",
                "area": 200.50,
                "percentage": 20.0
            }
        ],
        "speciesDistribution": [
            {
                "speciesName": "马尾松",
                "area": 400.00,
                "percentage": 40.0
            },
            {
                "speciesName": "杉木",
                "area": 300.00,
                "percentage": 30.0
            }
        ]
    }
}
```

## 8. 仪表板接口

### 8.1 获取统计数据
- **接口地址**: `GET /dashboard/statistics`
- **权限要求**: 需要认证
- **响应示例**:
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "totalUsers": 100,
        "totalStands": 500,
        "totalSpecies": 50,
        "totalArea": 10000.00,
        "recentActivities": [
            {
                "id": 1,
                "type": "STAND_CREATE",
                "description": "创建了新的林分：松树林分",
                "timestamp": "2024-01-15T10:30:00"
            }
        ]
    }
}
```

## 9. 错误响应格式

### 9.1 业务异常
```json
{
    "code": 400,
    "msg": "用户名已存在",
    "data": null
}
```

### 9.2 权限不足
```json
{
    "code": 403,
    "msg": "权限不足",
    "data": null
}
```

### 9.3 认证失败
```json
{
    "code": 401,
    "msg": "用户名或密码错误",
    "data": null
}
```

### 9.4 参数验证失败
```json
{
    "code": 400,
    "msg": "standName: 林分名称不能为空; area: 面积必须大于0",
    "data": null
}
```

### 9.5 服务器内部错误
```json
{
    "code": 500,
    "msg": "服务器内部错误",
    "data": null
}
```

## 10. 分页参数说明

### 10.1 分页参数
- `page`: 页码，从0开始
- `size`: 每页大小，默认10
- `sort`: 排序字段，格式为 `字段名,排序方向`
  - 示例：`sort=standName,asc` 或 `sort=createTime,desc`

### 10.2 分页响应格式
```json
{
    "content": [...],           // 数据列表
    "totalElements": 100,       // 总记录数
    "totalPages": 10,          // 总页数
    "size": 10,                // 每页大小
    "number": 0,               // 当前页码
    "first": true,             // 是否第一页
    "last": false,             // 是否最后一页
    "numberOfElements": 10     // 当前页记录数
}
```

## 11. 注意事项

1. **认证要求**: 除登录接口外，所有接口都需要在请求头中携带有效的JWT Token
2. **权限控制**: 不同角色和权限的用户只能访问相应的接口
3. **参数验证**: 所有POST和PUT请求的参数都会进行验证，不符合要求会返回400错误
4. **分页查询**: 支持分页的接口默认每页10条记录，最大不超过100条
5. **排序**: 支持多字段排序，用逗号分隔
6. **模糊查询**: 支持字符串字段的模糊查询（LIKE %value%）
7. **时间格式**: 所有时间字段使用ISO 8601格式（yyyy-MM-ddTHH:mm:ss）

## 12. 开发环境信息

- **服务器地址**: `http://localhost:8080`
- **API前缀**: `/api`
- **数据库**: MySQL 8.0+
- **JWT密钥**: `forest-secret-key`
- **JWT有效期**: 24小时（86400秒） 