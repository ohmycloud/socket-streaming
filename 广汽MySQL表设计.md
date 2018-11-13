[toc]

# 常用字段类型和长度约定
|字段|说明|类型和长度|
|:---------------|:-------|:---------------|
|vin             | 车架号  | varchar(17)    |
|region          | 区域    | varchar(20)    |
|vintype         | 车型    | tinyint(1)     |
|usage           | 车辆用途 | tinyint(1)     |
|year            | 年份    | varchar(4)     |
|day             | 天      | datime         |
|month           | 月份区间 | varchar(2)     |
|charge_type     | 充电类型 | tinyint(1)     |
|trip_type       | 行程类型 | tinyint(1)     |
|evaluation_type | 指数类型 | tinyint(1)     |
|speed_range     | 速度范围 | tinyint(1)     |
|soc_range       | 电量范围 | tinyint(1)     |
|depth_range     | 制动深度 | tinyint(1)     |
|current_range   | 充电电流 | tinyint(1)     |
|temp_type       | 温度范围 | tinyint(1)     | 
|time_type       | 时间类型 | tinyint(1)     |
|veh_runmode     | 驾驶模式 | tinyint(1)     |
|pedal_type      | 踏板类型 | tinyint(1)     |
|parts_type      | 零件类型 | tinyint(1)     |
|value_type      | 指标类型 | tinyint(1)     |

# MySQL 表

|表名                                  |数据源    | 说明         | 
|:------------------------------------|:--------|:----------------|
| vehicle_region_rank  |  |  |  
| vehicle_monthly_grow  |  |  |  
| vehicle_evaluation  |  |  |  
| vehicle_type_evaluation  |  |  |  
| vehicle_evaluation_details  |  |  |  
| vehicle_type_evaluation_details  |  |  |  
| driving_period_summary  |  |  |  
| driving_intensity_summary  |  |  |  
| driving_harsh_summary  |  |  |  
| brake_acc_summary  |  |  |  
| electricity_mileage_duration_summary  |  |  |  
| pedal_speed_summary  |  |  |  
| pedal_depth_summary  |  |  |  
| acc_distribution_summary  |  |  |  
| driving_period  |  |  |  
| driving_intensity  |  |  |  
| driving_sharp  |  |  |  
| brake_acc  |  |  |  
| electricity_mileage_duration  |  |  |  
| pedal_cnt_dept  |  |  |  
| pedal_depth  |  |  |  
| acc_distribution  |  |  |  
| charge_period_summary  |  |  |  
| soc_range_summary  |  |  |  
| charge_intensity_summary  |  |  |  
| charge_current_smoothness_summary  |  |  |  
| soc_range_charge_quality_summary  |  |  |  
| charge_interval_summary  |  |  |  
| charge_period  |  |  |  
| soc_start_end  |  |  |  
| charge_intensity  |  |  |  
| charge_stablity  |  |  |  
| charge_interval  |  |  |  
| tbst_dis_all  |  |  |  
| thlt_dis_all  |  |  |  
| tbtdp_dis_all  |  |  |  
| tbht_gt45_per_all  |  |  |  
| tbtdv_dis_all  |  |  |  
| tbvdv_dis_all  |  |  |  
| ttr_dis_all  |  |  |  
| tvr_dis_all  |  |  |  
| tbcio_tdv_dis_all  |  |  |  
| tbci_tr_dis_all  |  |  |  
| charge_ssocr_dis_summary  |  |  |  
| soc_rm_dis_all  |  |  |  
| soc_rc_dis_all  |  |  |  
| soc_cv_dis_all  |  |  |  
| tbst_dis  |  |  |  
| thlt_dis  |  |  |  
| tbtdp_dis  |  |  |  
| tbht_gt45_per  |  |  |  
| tbtdv_dis  |  |  |  
| tbvdv_dis  |  |  |  
| ttr_dis  |  |  |  
| tvr_dis  |  |  |  
| tbcio_tdv_dis  |  |  |  
| tbci_tr_dis  |  |  |  
| charge_ssocr_dis  |  |  |  
| soc_rm_dis  |  |  |  
| soc_rc_dis  |  |  |  
| soc_vc_dis  |  |  |  
| parts_summary  |  |  |  
| dcdc_summary  |  |  |  
| ptc_ecp_summary  |  |  |  
| 3to2_valve_summary  |  |  |  
| parts  |  |  |  
| speed_torque_relation  |  |  |  
| speed_torque_temp_relation  |  |  |  
| ptc_ecp  |  |  |  
| 3to2_valve  |  |  |  
| alm_ana_statis_all  |  |  |  
| avg_noalm_inter_all  |  |  |  
| alm_ana_statis  |  |  |  
| avg_noalm_inter  |  |  |  
|bat_temp_dis_summary|    |行程最高/低/起始温度分布整体区域| 
|bat_temp_perd_dis_summary|    |行程电池温差(7、15)/大于45°时长占比分布|
|temp_vol_dis_dis_summary|    |行程电池温差/压差数值分布整体区域|
|temp_vol_perd_summary|    |行程各温度区间/电压区间时长分布整体区域|
|bat_watrio_temp_dif_summary|    |行程电池冷却水入口和出口温差数值分布整体区域|
|bat_watri_temp_range_summary|    |电池冷却水入口温度区间时长分布整体区域|
|charge_ssocr_dis_summary|    |车辆SOC区间行驶里程分布整体区域|
|soc_eq_mile_rate_summary|    |车辆SOC区间充电量/行驶里程/充电速率分布整体区域|
|bat_temp_dis|    |行程最高/低/起始温度分布单车|
|bat_temp_perd_dis|    |行程电池温差(7、15)/大于45°时长占比分布单车|
|temp_vol_dis_dis|    |行程电池温差/压差数值分布单车|
|temp_vol_perd|    |行程各温度区间/电压区间时长分布单车|
|bat_watrio_temp_dif|    |行程电池冷却水入口和出口温差数值分布单车|
|bat_watri_temp_range|    |电池冷却水入口温度区间时长分布单车|
|charge_ssocr_dis|    |车辆SOC区间行驶里程分布单车|
|soc_eq_mile_rate|    |车辆SOC区间充电量/行驶里程/充电速率分布单车|



# 首页

- 区域车辆数量分布

```sql
create table vehicle_region_rank (
    day datetime NOT NULL COMMENT  '出行日期,yyyy-MM-dd 格式',
    vintype tinyint(1)  COMMENT    '车型：0-A51, 1-A2A, 2-A75, 3-A5H，4-新能源车',
    region varchar(20) COMMENT     '区域',
    rank int(11) default 0 COMMENT '车辆数量',
    UNIQUE KEY `unique_vehicle_region_rank` (`day`,`vintype`, `region`),
    KEY `idx_day` (`day`),
    KEY `idx_vintype` (`vintype`),
    KEY `idx_region` (`region`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不同车型不同区域车辆数量分布表';
```

- 月增长趋势

```sql
create table vehicle_monthly_grow (
    vintype tinyint(1)  COMMENT       '车型：0-A51, 1-A2A, 2-A75, 3-A5H',
    year varchar(4) NOT NULL COMMENT  '年份',
    month varchar(2) NOT NULL COMMENT '月份：1-1月，1-2月，... 12-12月', 
    rank int(11) default 0 COMMENT    '车辆数量',
    UNIQUE KEY `unique_vehicle_monthly_grow` (`vintype`, `year`, `month`),
    KEY `idx_vintype` (`vin_type`),
    KEY `idx_year` (`year`),
    KEY `idx_month` (`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不同车型月增长趋势表';
```

# 综合分析

## 整车评估

```sql
create table vehicle_evaluation (
    vin varchar(17) NOT NULL COMMENT            '车架号',  
    evaluation_type tinyint(1) NOT NULL COMMENT '0-经济性，1-安全性，2-可靠性，3-环境适用性',
    value int(3) default 0 COMMENT              '得分',
    UNIQUE KEY `vin` (`vin`),
    KEY `idx_vin` (`vin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单车整车评估指数表';
```

- 整车评估

```sql
create table vehicle_type_evaluation (
    vintype tinyint(1) NOT NULL COMMENT         '车型：0-A51, 1-A2A, 2-A75, 3-A5H',
    evaluation_type tinyint(1) NOT NULL COMMENT '0-经济性，1-安全性，2-可靠性，3-环境适用性',
    value int(3) default 0 COMMENT              '得分',
    UNIQUE KEY `vintype` (`vintype`),
    KEY `idx_vintype` (`vintype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型评估指数表';
```


```sql
create table vehicle_evaluation_details (
    vin varchar(17) NOT NULL COMMENT                                  '车架号',  
    malfunction_check_index int(11) default 0 COMMENT                 '故障检测指数',
    mileage_decline_index int(11) default 0 COMMENT                   '里程衰退指数',
    soc_range_mileage_utilization_index int(11) default 0 COMMENT     '不同季节SOC区间里程利用率指数',
    energy_consumption_sd_index int(11) default 0 COMMENT             '不同季节百公里能耗标准差指数',
    pure_electric_mileage_utilization_index int(11) default 0 COMMENT '纯电里程利用率指数',
    energy_consumption_index int(11) default 0 COMMENT                '百公里能耗指数',
    UNIQUE KEY `vin` (`vin`),
    KEY `idx_vin` (`vin`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单车整车评估细项表';
```

- 整车评估细项对比

```sql
create table vehicle_type_evaluation_details (
    vintype tinyint(1) NOT NULL COMMENT                               '车型：0-A51, 1-A2A, 2-A75, 3-A5H',
    malfunction_check_index int(11) default 0 COMMENT                 '故障检测指数',
    mileage_decline_index int(11) default 0 COMMENT                   '里程衰退指数',
    soc_range_mileage_utilization_index int(11) default 0 COMMENT     '不同季节SOC区间里程利用率指数',
    energy_consumption_sd_index int(11) default 0 COMMENT             '不同季节百公里能耗标准差指数',
    pure_electric_mileage_utilization_index int(11) default 0 COMMENT '纯电里程利用率指数',
    energy_consumption_index int(11) default 0 COMMENT                '百公里能耗指数',
    UNIQUE KEY `vintype` (`vintype`),
    KEY `idx_vintype` (`vintype`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型评估细项表';
```

## 整车监控-区域

- 区域车辆分布

取 latest 表最新一条记录（HBase）

- 单车工况详情

取 latest 表最新一条记录。

- 区域详情
  - 区域车型占比
  - 车辆用途类型占比

## 整车监控-单车

- 单车信息详情

取 latest 表中的最新一条数据

- 车辆状态

取 latest 表中的最新一条数据。

- 实时监控可视化

取 can_signal 数据。

- 单体电池温度与电压

TODO

### 单车监控（驾驶行为）

### 单车监控（实时监控）

### 单车监控（充电行为）

### 单车监控（电池）

### 单车监控（其他零部件）

### 单车监控（故障）

### 单车监控（单车评估）

# 用户行为分析

## 驾驶行为-整体区域

**Warning:** 当区域和车型没有选择时， 则是计算所有区域和所有车型。

- 出行时段分布


```sql
create table driving_period_summary (
  year varchar(4) NOT NULL COMMENT                        '年份',
  month varchar(2) NOT NULL COMMENT                       '月份：1-1月，1-2月，... 12-12月', 
  region varchar(20) NOT NULL COMMENT                     '区域： 包括所有区域',
  vintype tinyint(1) NOT NULL COMMENT                     '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  usage tinyint(1) NOT NULL COMMENT                       '车辆用途：0-出租车，1-私家车',
  driving_peroid varchar(2) NOT NULL COMMENT              '出行时段, 0-0:00~1:00, 1-1:00~2:00, ...23-23:00~24:00',
  driving_count int(11) default 0 NOT NULL COMMENT        '出行次数',
  driving_period_percent double(5,2) default 0.00 COMMENT '出行频次占比',
  UNIQUE KEY `unique_driving_period_summary` (`year`, `month`, `region`,`vintype`, `usage`, `driving_peroid`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_usage` (`usage`),
  KEY `idx_driving_peroid` (`driving_peroid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行时段分布表（驾驶行为-整体区域）';
```

- 出行强度

```sql
create table driving_intensity_summary (
  year varchar(4) NOT NULL COMMENT        '年份',
  month varchar(2) NOT NULL COMMENT       '月份：1-1月，1-2月，... 12-12月',   
  region varchar(20) NOT NULL COMMENT     '区域： 包括所有区域',
  vintype tinyint(1) NOT NULL COMMENT     '车型：0-A51, 1-A2A, 2-A75, 3-A5H, 4-所有车型',
  usage tinyint(1) NOT NULL COMMENT       '车辆用途：0-出租车，1-私家车',
  driving_days int(11) default 0 COMMENT  '出行天数',
  driving_count int(11) default 0 COMMENT '出行次数',
  driving_duration double(16,2) default 0.00 COMMENT '出行时长',
  UNIQUE KEY `unique_driving_intensity_summary` (`year`, `month`, `region`, `vintype`, `usage`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),  
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_usage` (`usage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行强度表（驾驶行为-整体区域）';
```

> driving_duration 出行时长的单位是（天，小时，分钟？），类型是（int, double?）

- 出行平稳度-三急统计

```sql
create table driving_harsh_summary (
  year varchar(4) NOT NULL COMMENT         '年份',
  month varchar(2) NOT NULL COMMENT        '月份：1-1月，1-2月，... 12-12月',   
  region varchar(20) NOT NULL COMMENT      '区域： 包括所有区域',
  vintype tinyint(1) NOT NULL COMMENT      '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  usage tinyint(1) NOT NULL COMMENT        '车辆用途：0-出租车，1-私家车',
  veh_runmode tinyint(1) NOT NULL COMMENT  '驾驶模式：0-全部驾驶模式, 1-Normal, 2-ECO',
  harsh_acc int(11) default 0 COMMENT      '每百公里急加速次数',
  harsh_slowdown int(11) default 0 COMMENT '每百公里急减速次数',
  harsh_stop int(11) default 0 COMMENT     '每百公里急停次数',
  UNIQUE KEY `unique_driving_harsh_summary` (`year`, `month`, `region`, `vintype`, `usage`, `veh_runmode`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_usage` (`usage`),
  KEY `idx_veh_runmode` (`veh_runmode`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行平稳度表（驾驶行为-整体区域）';
```

- 出行平稳度-加速与制动次数与深度分布
```sql
create table brake_acc_summary (
  year varchar(4) NOT NULL COMMENT         '年份',
  month varchar(2) NOT NULL COMMENT        '月份：1-1月，1-2月，... 12-12月',   
  region varchar(20) NOT NULL COMMENT      '区域：包括所有区域',
  vintype tinyint(1) NOT NULL COMMENT      '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  usage tinyint(1) NOT NULL COMMENT        '车辆用途：0-出租车，1-私家车',
  veh_runmode tinyint(1) NOT NULL COMMENT  '驾驶模式：0-全部驾驶模式, 1-Normal, 2-ECO',
  pedal_type tinyint(1) NOT NULL COMMENT   '0-制动, 1-加速',
  pedal_count int(11) default 0 COMMENT    '每百公里制动/加速次数', 
  pedal_depth double(16,2) default 0.00    '每百公里制动/加速踏板踩踏深度',
  UNIQUE KEY `unique_brake_acc_summary` (`year`, `month`, `region`, `vintype`, `usage`, `veh_runmode`, `pedal_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_usage` (`usage`),
  KEY `idx_veh_runmode` (`veh_runmode`),
  KEY `idx_pedal_type` (`pedal_type`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加速与制动次数与深度分布表（驾驶行为-整体区域）';
```

- 出行工况-各速度区间电耗/里程/时长占比

```sql
create table electricity_mileage_duration_summary (
  day datetime NOT NULL COMMENT            '出行日期,yyyy-MM-dd 格式',
  region varchar(20) NOT NULL COMMENT      '区域： 包括所有区域',
  vintype tinyint(1) NOT NULL COMMENT      '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  usage tinyint(1) NOT NULL COMMENT        '车辆用途：0-出租车，1-私家车',
  veh_runmode tinyint(1) NOT NULL COMMENT  '驾驶模式：0-全部驾驶模式, 1-Normal, 2-ECO',
  speed_range tinyint(1) NOT NULL COMMENT  '速度区间：0-[0~20],1-[20~40],2-[40~60],3-[60~80],4-[80~100],5-[100~120],6-[120~140],7-[140+]',
  electricity_consumption double(16,2) default 0.00 COMMENT '电耗',
  mileage double(16,2) default 0.00 COMMENT                 '里程',
  duration double(16,2) default 0.00 COMMENT                '时长'
  UNIQUE KEY `unique_electricity_mileage_duration_summary` (`day`, `region`, `vintype`, `usage`, `veh_runmode`, `speed_range`),
  KEY `idx_day` (`day`),
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_usage` (`usage`),
  KEY `idx_veh_runmode` (`veh_runmode`),  
  KEY `idx_speedrange` (`speed_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各速度区间电耗/里程/时长表（驾驶行为-整体区域）';
```

- 出行工况-各速度区段制动/加速踏板次数与平均深度

```sql
create table pedal_speed_summary (
    day datetime NOT NULL COMMENT                '出行日期,yyyy-MM-dd 格式',
    region varchar(20) NOT NULL COMMENT          '区域： 包括所有区域',
    vintype tinyint(1) NOT NULL COMMENT          '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
    usage tinyint(1) NOT NULL COMMENT            '车辆用途：0-出租车，1-私家车',
    pedal_type tinyint(1) NOT NULL COMMENT       '0-制动, 1-加速',
    speed_range tinyint(1) NOT NULL COMMENT      '速度区间：0-[0~20],1-[20~40],2-[40~60],3-[60~80],4-[80~100],5-[100~120],6-[120~140],7-[140+]',
    pedal_count int(11) default 0 COMMENT        '每百公里制动/加速踏板踩踏次数',
    pedal_depth double(5,3) default 0.00 COMMENT '制动/加速踏板踩踏深度',
    UNIQUE KEY `unique_pedal_speed_summary` (`day`, `region`, `vintype`, `usage`,  `pedal_type`, `speed_range`),
    KEY `idx_day` (`day`),
    KEY `idx_region` (`region`),
    KEY `idx_vintype` (`vintype`),
    KEY `idx_usage` (`usage`),
    KEY `idx_pedal_type` (`pedal_type`),
    KEY `idx_speed_range` (`speed_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='制动/加速踏板速度区段频次和深度分布表（驾驶行为-整体区域）';
```

- 出行工况-制动/加速踏板深度区段频次分布

```sql
create table pedal_depth_summary (
    day datetime NOT NULL COMMENT            '出行日期,yyyy-MM-dd 格式',
    region varchar(20) NOT NULL COMMENT      '区域： 包括所有区域',
    vintype tinyint(1) NOT NULL COMMENT      '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
    usage tinyint(1) NOT NULL COMMENT        '车辆用途：0-出租车，1-私家车',
    veh_runmode tinyint(1) NOT NULL COMMENT  '驾驶模式：0-全部驾驶模式, 1-Normal, 2-ECO',
    pedal_type tinyint(1) NOT NULL COMMENT   '0-制动, 1-加速',
    depth_range tinyint(1) NOT NULL COMMENT  '制动/加速踏板踩踏深度：0-[0~10%],1-[10%~20%],2-[20%~30%],3-[30%~40%],4-[40%~50%],5-[50%~60%],6-[60%~70%],7-[70%~80%],8-[80%-90%],9-[90%~100%]',
    pedal_count int(11) default 0 COMMENT    '每百公里制动/加速踏板踩踏次数',
    UNIQUE KEY `unique_pedal_depth_summary` (`day`, `region`, `vintype`, `usage`, `veh_runmode`, `pedal_type`, `depth_range`),
    KEY `idx_day` (`day`),    
    KEY `idx_region` (`region`),
    KEY `idx_vintype` (`vintype`),
    KEY `idx_usage` (`usage`),
    KEY `idx_veh_runmode` (`veh_runmode`),
    KEY `idx_pedal_type` (`pedal_type`),
    KEY `idx_depth_range` (`depth_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='制动/加速踏板深度区段频次分布（驾驶行为-整体区域）';
```

- 出行工况-各速度区间车辆加速度分布

```sql
create table acc_distribution_summary (
    day datetime NOT NULL COMMENT                       '出行日期,yyyy-MM-dd 格式',
    region varchar(20) NOT NULL COMMENT                 '区域： 包括所有区域',
    vintype tinyint(1) NOT NULL COMMENT                 '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
    usage tinyint(1) NOT NULL COMMENT                   '车辆用途：0-出租车，1-私家车',
    speed_range tinyint(1) NOT NULL COMMENT             '速度区间：0-[0~20],1-[20~40],2-[40~60],3-[60~80],4-[80~100],5-[100~120],6-[120~140],7-[140+]',
    min_acc double(16,2) default 0.0 COMMENT            '最小加速度',
    max_acc double(16,2) default 0.0 COMMENT            '最大加速度',
    quarter_acc double(16,2) default 0.0 COMMENT        '四分之一分位加速度',
    three_quarters_acc double(16,2) default 0.0 COMMENT '四分之三分位加速度',
    median_acc double(16,2) default 0.00  COMMENT       '加速度中位数',
    UNIQUE KEY `unique_acc_distribution_summary` (`day`, `region`, `vintype`, `usage`, `speed_range`),
    KEY `idx_day` (`day`),    
    KEY `idx_region` (`region`),
    KEY `idx_vintype` (`vintype`),
    KEY `idx_usage` (`usage`),
    KEY `idx_speed_range` (`speed_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各速度区间速度分布表（驾驶行为-整体区域）';
```

## 驾驶行为-单车

**warning:** 同区域车型平均和同区域用途平均可以在日表中由 sql 过滤计算。

- 单车信息详情

**TODO**

- 出行时段分布

```sql
create table driving_period (
  day datetime NOT NULL COMMENT              '出行日期,yyyy-MM-dd 格式',
  vin varchar(17) NOT NULL COMMENT           '车架号',
  driving_peroid varchar(2) NOT NULL COMMENT '出行时段, 0-0:00~1:00, 1-1:00~2:00, ...23-23:00~24:00',
  driving_count int(11) default 0 COMMENT    '出行频次',
  UNIQUE KEY `unique_driving_period` (`day`, `vin`, `driving_peroid`),
  KEY `idx_day` (`day`),  
  KEY `idx_vin` (`vin`),
  KEY `idx_driving_peroid` (`driving_peroid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行时段分布表（单车）';
```

- 出行强度
```sql
create table driving_intensity (
  year varchar(4) NOT NULL COMMENT                   '年份',
  month varchar(2) NOT NULL COMMENT                  '月份：1-1月，1-2月，... 12-12月',   
  vin varchar(17) NOT NULL COMMENT                   '车架号',
  driving_days int(11) default 0 COMMENT             '出行天数',
  driving_count int(11) default 0 COMMENT            '出行次数',
  driving_duration double(16,2) default 0.00 COMMENT '出行时长',
  UNIQUE KEY `unique_driving_intensity` (`year`, `month`, `vin`),
  KEY `idx_vin` (`vin`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行强度表（单车）';
```

- 出行平稳度-三急统计

```sql
create table driving_sharp (
  year varchar(4) NOT NULL COMMENT         '年份',
  month varchar(2) NOT NULL COMMENT        '月份：1-1月，1-2月，... 12-12月',   
  vin varchar(17) NOT NULL COMMENT         '车架号',
  harsh_acc int(11) default 0 COMMENT      '每百公里急加速次数',
  harsh_slowdown int(11) default 0 COMMENT '每百公里急减速次数',
  harsh_stop int(11) default 0 COMMENT     '每百公里急停次数',
  UNIQUE KEY `unique_driving_sharp` (`year`, `month`, `vin`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),  
  KEY `idx_vin` (`vin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行平稳度-三急统计表（单车）';
```

- 出行平稳度-加速与制动分布

```sql
create table brake_acc (
  year varchar(4) NOT NULL COMMENT       '年份',
  month varchar(2) NOT NULL COMMENT      '月份：1-1月，1-2月，... 12-12月',   
  vin varchar(17) NOT NULL COMMENT       '车架号',
  pedal_type tinyint(1) NOT NULL COMMENT '0-制动, 1-加速',
  pedal_count int(11) default 0 COMMENT  '每百公里制动/加速次数', 
  pedal_depth double(16,2) default 0.00  '每百公里制动/加速踏板踩踏深度',
  UNIQUE KEY `unique_brake_acc` (`year`, `month`, `vin`, `pedal_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vin` (`vin`),
  KEY `idx_pedal_type` (`pedal_type`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加速与制动次数与深度分布表（单车）';
```

- 出行工况-各速度区间电耗/里程/时长占比

```sql
create table electricity_mileage_duration (
    day datetime NOT NULL COMMENT           '出行日期,yyyy-MM-dd 格式',
    vin varchar(17) NOT NULL COMMENT        '车架号',
    speed_range tinyint(1) NOT NULL COMMENT '速度区间：0-[0~20],1-[20~40],2-[40~60],3-[60~80],4-[80~100],5-[100~120],6-[120~140],7-[140+]',
    electricity_consumption double(16,2) default 0.00 COMMENT '电耗',
    mileage double(16,2) default 0.00 COMMENT                 '里程',
    duration double(16,2) default 0.00 COMMENT                '时长',
    UNIQUE KEY `unique_electricity_mileage_duration` (`day`, `vin`, `speed_range`), 
    KEY `idx_day` (`day`),
    KEY `idx_vin` (`vin`),    
    KEY `idx_speedrange` (`speed_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各速度区间电耗、里程和时长表（单车）';
```

- 出行工况-制动/加速踏板次数与深度分布

```sql
create table pedal_cnt_dept (
    day datetime NOT NULL COMMENT                '出行日期,yyyy-MM-dd 格式',
    pedal_type tinyint(1) NOT NULL COMMENT       '0-制动, 1-加速',
    speed_range tinyint(1) NOT NULL COMMENT      '速度区间：0-[0~20],1-[20~40],2-[40~60],3-[60~80],4-[80~100],5-[100~120],6-[120~140],7-[140+]',
    pedal_count int(11) default 0 COMMENT        '每百公里制动/加速踏板踩踏次数',
    pedal_depth double(5,3) default 0.00 COMMENT '制动/加速踏板踩踏深度',
    UNIQUE KEY `unique_pedal_cnt_dept` (`day`, `pedal_type`, `speed_range`),
    KEY `idx_day` (`day`),
    KEY `idx_pedal_type` (`pedal_type`),
    KEY `idx_speed_range` (`speed_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='制动/加速踏板速度区段频次和深度分布表（单车）';
```

- 出行工况-制动/加速踏板深度区段频次分布

```sql
create table pedal_depth (
    day datetime NOT NULL COMMENT           '出行日期,yyyy-MM-dd 格式',
    vin varchar(17) NOT NULL COMMENT        '车架号',
    pedal_type tinyint(1) NOT NULL COMMENT  '0-制动, 1-加速',
    depth_range tinyint(1) NOT NULL COMMENT '制动踏板/加速踏板的踩踏深度：0-[0~10%],1-[10%~20%],2-[20%~30%],3-[30%~40%],4-[40%~50%],5-[50%~60%],6-[60%~70%],7-[70%~80%],8-[80%-90%],9-[90%~100%]',
    pedal_count int(11) default 0 COMMENT   '制动/加速踏板踩踏次数',
    UNIQUE KEY `unique_pedal_depth` (`day`, `vin`, `pedal_type`, `depth_range`),
    KEY `idx_day` (`day`),    
    KEY `idx_vin` (`vin`),
    KEY `idx_pedal_type` (`pedal_type`),
    KEY `idx_depth_range` (`depth_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='制动/加速踏板深度区段频次分布表（单车）';
```

- 出行工况-各速度区间车辆加速度分布
```sql
create table acc_distribution (
    day datetime NOT NULL COMMENT                        '出行日期,yyyy-MM-dd 格式',
    speed_range tinyint(1) NOT NULL COMMENT              '0-[0~20],1-[20~40],2-[40~60],3-[60~80],4-[80~100],5-[100~120],6-[120~140],7-[140+]',
    max_acc double(16,2) default 0.00 COMMENT            '最大加速度',
    min_acc double(16,2) default 0.00 COMMENT            '最小加速度',
    quarter_acc double(16,2) default 0.00 COMMENT        '四分之一分位加速度',
    three_quarters_acc double(16,2) default 0.00 COMMENT '四分之三分位加速度',
    median_acc double(16,2) default 0.00 COMMENT         '加速度中位数',
    UNIQUE KEY `unique_acc_distribution_summary` (`day`, `speed_range`),
    KEY `idx_day` (`day`),    
    KEY `idx_speed_range` (`speed_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各速度区间速度分布表（单车）';
```


## 充电行为-整体区域

- 充电时段分布

**TODO：纵坐标是充电次数吗？*

```sql
create table charge_period_summary (
    year varchar(4) NOT NULL COMMENT          '年份',
    month varchar(2) NOT NULL COMMENT         '月份：1-1月，1-2月，... 12-12月', 
    region varchar(20) NOT NULL COMMENT       '区域： 包括所有区域',
    vintype tinyint(1) NOT NULL COMMENT       '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
    usage tinyint(1) NOT NULL COMMENT         '车辆用途：0-出租车，1-私家车',
    charge_type tinyint(1) NOT NULL COMMENT   '充电类型： 0-快充, 1-慢充',
    charge_period varchar(2) NOT NULL COMMENT '充电时段：0-0:00~1:00, 1-1:00~2:00, ...23-23:00~24:00',
    charge_count int(11) default 0 COMMENT    '充电次数',
    UNIQUE KEY `unique_charge_period_summary` (`year`, `month`, `region`, `vintype`, `usage`, `charge_type`, `charge_period`),
    KEY `idx_year` (`year`),
    KEY `idx_month` (`month`),
    KEY `idx_region` (`region`),
    KEY `idx_vintype` (`vintype`),
    KEY `idx_usage` (`usage`),
    KEY `idx_charge_type` (`charge_type`),
    KEY `idx_charge_period` (`charge_period`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电时段频次分布表（整体区域）';
```

- 充电起始/结束SOC范围分布

```sql
create table soc_range_summary (
    day datetime NOT NULL COMMENT           '充电日期,yyyy-MM-dd 格式',
    region varchar(20) NOT NULL COMMENT     '区域： 包括所有区域',
    vintype tinyint(1) NOT NULL COMMENT     '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
    usage tinyint(1) NOT NULL COMMENT       '车辆用途：0-出租车，1-私家车',
    charge_type tinyint(1) NOT NULL COMMENT '充电类型： 0-快充, 1-慢充',
    soc_range tinyint(1) NOT NULL COMMENT   '0-[0~10%], 1-[10%~20%],2-[20%~30%],3-[30%~40%],4-[40%~50%],5-[50%~60%],6-[60%~70%],7-[70%~80%],8-[80%-90%],9-[90%~100%]',
    start_soc int(11) default 0 COMMENT     '充电起始SOC',
    end_soc int(11) default 0 COMMENT       '充电结束SOC',
    UNIQUE KEY `unique_soc_range_summary` (`day`, `region`, `vintype`, `usage`, `charge_type`, `soc_range`),
    KEY `idx_day` (`day`),    
    KEY `idx_region` (`region`),
    KEY `idx_vintype` (`vintype`),
    KEY `idx_usage` (`usage`),
    KEY `idx_charge_type` (`charge_type`),
    KEY `idx_charge_period` (`soc_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电起始/结束SOC范围分布表（整体区域）';
```

- 充电强度

```sql
create table charge_intensity_summary (
  year varchar(4) NOT NULL COMMENT                           '年份',
  month varchar(2) NOT NULL COMMENT                          '月份：1-1月，1-2月，... 12-12月', 
  vintype tinyint(1) NOT NULL COMMENT                        '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  region varchar(20) NOT NULL COMMENT                        '区域： 含所有区域',
  charge_type tinyint(1) NOT NULL COMMENT                    '0-快充, 1-慢充',
  charging_days int(11) default 0 COMMENT                    '充电天数',
  charge_duration double(16,2) default 0.00 NOT NULL COMMENT '充电时长',
  charge_count int(11) default 0 NOT NULL COMMENT            '充电次数',
  UNIQUE KEY `unique_charge_intensity_summary` (`year`, `month`, `vintype`, `region`, `charge_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_region` (`region`),
  KEY `idx_charge_type` (`charge_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电强度表（整体区域）';
```

- 充电工况-充电电流平稳度

```sql
create table charge_current_smoothness_summary (
  day datetime NOT NULL COMMENT             '日期',
  region varchar(20) NOT NULL COMMENT       '区域：包括所有区域',  
  vintype tinyint(1) NOT NULL COMMENT       '车型：0-A51, 1-A2A, 2-A75, 3-A5H',
  charge_type tinyint(1) NOT NULL COMMENT   '充电类型：0-慢充，1-快充',
  usage tinyint(1) NOT NULL COMMENT         '车辆用途：0-出租车，1-私家车',
  current_range varchar(2) NOT NULL COMMENT '电流区间：0-[0~5A],1-[5~10A],2-[15-20A]..., 38-[190~195A],39-[195~200A]',
  charge_count int(11) default 0 COMMENT    '充电次数',
  UNIQUE KEY `unique_charge_current_smoothness_summary` (`day`, `region`, `vintype`, `charge_type`, `usage`, `current_range`),
  KEY `idx_day` (`day`),  
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `charge_type` (`charge_type`),
  KEY `idx_usage` (`usage`),
  KEY `idx_current_range` (`current_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电平稳度表（整体区域）';
```

- 充电工况-各SOC区间充电量分布

```sql
create table soc_range_charge_quality_summary (
  day datetime NOT NULL COMMENT           '充电日期,yyyy-MM-dd 格式',
  region varchar(20) NOT NULL COMMENT     '区域： 车辆常驻地',  
  vintype tinyint(1) NOT NULL COMMENT     '车型：0-A51, 1-A2A, 2-A75, 3-A5H',
  charge_type tinyint(1) NOT NULL COMMENT '充电类型：0-慢充，1-快充',
  usage tinyint(1) NOT NULL COMMENT       '车辆用途：0-出租车，1-私家车',
  soc_range tinyint(1) NOT NULL COMMENT   '电量区间：0-[0~10%],1-[10~20%],2-[20~30%],3-[30~40%],4-[40~50%],5-[50~60%],6-[60-70%],7-[70-80%],8-[80-90%],9-[90~100%]',
  charge_soc int(11) default 0 COMMENT    '充电量',
  UNIQUE KEY `unique_soc_range_charge_quality_summary` (`day`, `region`, `vintype`, `charge_type`, `usage`, `soc_range`),
  KEY `idx_day` (`day`),
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`),
  KEY `charge_type` (`charge_type`),
  KEY `idx_usage` (`usage`),
  KEY `idx_soc_range` (`soc_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各SOC区间充电电量分布表（整体区域）';
```

- 充电间隔

```sql
create table charge_interval_summary (
  day datetime NOT NULL COMMENT                 '日期,yyyy-MM-dd 格式',
  region varchar(20) NOT NULL COMMENT           '区域： 包含所有区域',  
  vintype tinyint(1) NOT NULL COMMENT           '车型：0-所有车型，1-A51, 2-A2A, 3-A75, 4-A5H',
  interval double(5,2) default 0.00 COMMENT     '间隔时长',
  driving_count int(11) default 0 COMMENT       '行驶次数',
  mileage double(16,2) default 0.00 COMMENT     '行驶里程',
  discharging double(16,2) default 0.00 COMMENT '放电电量',
  UNIQUE KEY `unique_charge_interval_summary` (`day`, `region`, `vintype`),
  KEY `idx_day` (`day`)
  KEY `idx_region` (`region`),
  KEY `idx_vintype` (`vintype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电间隔行为表（整体区域）';
```

- 充电方式比例

TODO

## 充电行为-单车

- 充电时段分布

```sql
create table charge_period (
    year varchar(4) NOT NULL COMMENT          '年份',
    month varchar(2) NOT NULL COMMENT         '月份：1-1月，1-2月，... 12-12月', 
    vin varchar(17) NOT NULL COMMENT          '车架号',
    charge_type tinyint(1) NOT NULL COMMENT   '充电类型：0-快充, 1-慢充',
    charge_period varchar(2) NOT NULL COMMENT '充电时段：0-0:00~1:00, 1-1:00~2:00, ...23-23:00~24:00',
    charge_count int(11) default 0 COMMENT    '充电次数',
    UNIQUE KEY `unique_charge_period` (`year`, `month`, `vin`, `charge_type`, `charge_period`),
    KEY `idx_year` (`year`),
    KEY `idx_month` (`month`),
    KEY `idx_vin` (`vin`),
    KEY `idx_charge_type` (`charge_type`),
    KEY `idx_charge_period` (`charge_period`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电时段频次分布表';
```

- 充电起始/结束SOC范围分布

```sql
create table soc_start_end (
    day datetime NOT NULL COMMENT           '出行日期,yyyy-MM-dd 格式',
    vin varchar(17) NOT NULL COMMENT        '车架号',
    charge_type tinyint(1) NOT NULL COMMENT '充电类型： 0-快充, 1-慢充',
    soc_range tinyint(1) NOT NULL COMMENT   '0-[0~10%], 1-[10%~20%],2-[20%~30%],3-[30%~40%],4-[40%~50%],5-[50%~60%],6-[60%~70%],7-[70%~80%],8-[80%-90%],9-[90%~100%]',
    start_soc int(11) default 0 COMMENT     '充电起始SOC频次',
    end_soc int(11) default 0 COMMENT       '充电结束SOC频次',
    UNIQUE KEY `vin_day_charge_type_soc_range` ( `day`, `vin`, `charge_type`, `soc_range`),
    KEY `idx_day` (`day`),
    KEY `idx_vin` (`vin`),
    KEY `idx_charge_type` (`charge_type`),
    KEY `idx_charge_period` (`soc_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电起始/结束SOC范围分布表';
```

- 充电强度

```sql
create table charge_intensity (
  year varchar(4) NOT NULL COMMENT            '年份',
  month varchar(2) NOT NULL COMMENT           '月份：1-1月，1-2月，... 12-12月', 
  vin varchar(17) NOT NULL COMMENT            '车架号',
  charge_type tinyint(1) NOT NULL COMMENT     '0-快充, 1-慢充',
  has_charging tinyint(1) default "0" COMMENT '0-没有充电，1-有充电',
  UNIQUE KEY `unique_vin_day_vintype_region` (`year`, `month`, `vin`, `charge_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vin` (`vin`),
  KEY `idx_charge_type` (`charge_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电强度表（单车）';
```

- 充电工况-充电电流平稳度

```sql
create table charge_stablity (
    day datetime NOT NULL COMMENT             '出行日期,yyyy-MM-dd 格式',
    vin varchar(17) NOT NULL COMMENT          '车架号',
    charge_type tinyint(1) NOT NULL COMMENT   '充电类型： 0-快充, 1-慢充',
    current_range tinyint(1) NOT NULL COMMENT '充电电流, 0-20A, 1-40A, 2-60A,3-80A,4-100A',
    charge_count int(11) default 0 COMMENT    '充电频次',
    UNIQUE KEY `unique_charge_stablity` (`vin`, `day`, `charge_type`, `current_range`),
    KEY `idx_vin` (`vin`),
    KEY `idx_day` (`day`),
    KEY `idx_charge_type` (`charge_type`),
    KEY `idx_current_range` (`current_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电电流平稳度表';
```

- 充电工况-各SOC区间充电量分布

```sql
create table charge_interval (
  day datetime NOT NULL COMMENT                '出行日期,yyyy-MM-dd 格式',  
  vin varchar(17) NOT NULL COMMENT             '车架号',
  interval double(5,2) default 0.00 COMMENT    '间隔时长',
  driving_count int(11) default 0 COMMENT      '行驶次数',
  mileage double(16,2) default 0.00 COMMENT    '行驶里程',
  charge_soc double(16,2) default 0.00 COMMENT '充电量',
  UNIQUE KEY `vin_day` (`vin`, `day`),
  KEY `idx_vin` (`vin`),
  KEY `idx_day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电间隔行为表（单车）';
```

# 零部件分析
## 电池分析-整体区域
+ 行程电池起始/最高/低温度分布
    ```sql
    create table if not exists bat_temp_dis_summary(
      year varchar(2) NOT NULL COMMENT              '年份',
      month varchar(2) NOT NULL COMMENT             '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT           '区域',
      car_type varchar(10) NOT NULL COMMENT         '车型',
      car_usage varchar(10) NOT NULL COMMENT        '用途',
      trip_status char(1) NOT NULL COMMENT          '行程类型：0-驾驶 1-充电 2-休眠6小时',
      trip_st_all_flag tinyint(1) default 0 COMMENT '0-起始 1-所有',
      temp_hl_flag tinyint(1) default 0 COMMENT     '最高/低温度标识:0-最高 1-最低',
      bl_max int(3) COMMENT                         '箱线最大值',
      bl_min int(3) COMMENT                         '箱线最小值',
      bl_mid int(3) COMMENT                         '箱线中位数',
      bl_up_qutr int(3) COMMENT                     '箱线上1/4',
      bl_down_qutr int(3) COMMENT                   '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程最高/低/起始温度分布整体区域';
    ```
+ 行程电池温差(大于7、大于15)/最高温度大于45°时长占比分布
    ```sql
    create table if not exists bat_temp_perd_dis_summary(
      year varchar(2) NOT NULL COMMENT           '年份',
      month varchar(2) NOT NULL COMMENT          '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT        '区域',
      car_type varchar(10) NOT NULL COMMENT      '车型',
      car_usage varchar(10) NOT NULL COMMENT     '用途',
      trip_status char(1) NOT NULL COMMENT       '行程类型：0-驾驶 1-快充 2-慢充',
      temp_dif_flag tinyint(1) default 0 COMMENT '温差及大于45标识：0-温差大于7,1-温差大于15,2-最高温度大于45',
      bl_max int(3) COMMENT                      '箱线最大值',
      bl_min int(3) COMMENT                      '箱线最小值',
      bl_mid int(3) COMMENT                      '箱线中位数',
      bl_up_qutr int(3) COMMENT                  '箱线上1/4',
      bl_down_qutr int(3) COMMENT                '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程电池温差(7、15)/大于45°时长占比分布';
    ```

+ 行程电池温差/压差数值分布
    ```sql
    create table if not exists temp_vol_dis_dis_summary(
      year varchar(2) NOT NULL COMMENT              '年份',
      month varchar(2) NOT NULL COMMENT             '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT           '区域',
      car_type varchar(10) NOT NULL COMMENT         '车型',
      car_usage varchar(10) NOT NULL COMMENT        '用途',
      trip_type char(1) NOT NULL COMMENT            '行程类型：0-驾驶 1-快充 2-慢充',
      bat_temp_vol_dif tinyint(1) default 0 COMMENT '0-温差,1-压差',
      bl_max int(3) COMMENT                         '箱线最大值',
      bl_min int(3) COMMENT                         '箱线最小值',
      bl_mid int(3) COMMENT                         '箱线中位数',
      bl_up_qutr int(3) COMMENT                     '箱线上1/4',
      bl_down_qutr int(3) COMMENT                   '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程电池温差/压差数值分布整体区域';
    ```
    
+ 行程各温度/电压区间时长分布
    ```sql
    create table if not exists temp_vol_perd_summary(
      year varchar(2) NOT NULL COMMENT           '年份',
      month varchar(2) NOT NULL COMMENT          '月份：1-1月，1-2月，... 12-12月',
      region varchar(20) NOT NULL COMMENT        '区域',
      car_type varchar(10) NOT NULL COMMENT      '车型',
      car_usage varchar(10) NOT NULL COMMENT     '用途',
      trip_type char(1) NOT NULL COMMENT         '行程类型：0-驾驶 1-快充 2-慢充',
      temp_vol_flag tinyint(1) default 0 COMMENT '0-温度  1-电压',
      temp_range tinyint(1) default 0 COMMENT    '区间范围：0-[0~20] 1-[20~40] 2-[40~60] 3-[60~80]',
      bl_max int(4) COMMENT                      '箱线最大值',
      bl_min int(4) COMMENT                      '箱线最小值',
      bl_mid int(4) COMMENT                      '箱线中位数',
      bl_up_qutr int(4) COMMENT                  '箱线上1/4',
      bl_down_qutr int(4) COMMENT                '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程各温度区间/电压区间时长分布整体区域';
    ```
+ 行程电池冷却水入口和出口温差数值分布
    ```sql
    create table if not exists bat_watrio_temp_dif_summary(
      year varchar(2) NOT NULL COMMENT       '年份',
      month varchar(2) NOT NULL COMMENT      '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT    '区域',
      car_type varchar(10) NOT NULL COMMENT  '车型',
      car_usage varchar(10) NOT NULL COMMENT '用途',
      trip_type char(1) NOT NULL COMMENT     '行程类型：0-快冷 1-慢冷 2-电池加热',
      bl_max int(4) COMMENT                  '箱线最大值',
      bl_min int(4) COMMENT                  '箱线最小值',
      bl_mid int(4) COMMENT                  '箱线中位数',
      bl_up_qutr int(4) COMMENT              '箱线上1/4',
      bl_down_qutr int(4) COMMENT            '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程电池冷却水入口和出口温差数值分布整体区域';
   ```
+ 电池冷却水入口温度区间时长分布
    ```sql
    create table if not exists bat_watri_temp_range_summary(
      year varchar(2) NOT NULL COMMENT        '年份',
      month varchar(2) NOT NULL COMMENT       '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT     '区域',
      car_type varchar(10) NOT NULL COMMENT   '车型',
      car_usage varchar(10) NOT NULL COMMENT  '用途',
      trip_type char(1) NOT NULL COMMENT      '行程类型：0-快冷 1-慢冷 2-电池加热',
      temp_range tinyint(1) default 0 COMMENT '温度区间:0-[-40~-20],1-[-20~0],2-[0~20],3-[20~40]',
      bl_max int(4) COMMENT                   '箱线最大值',
      bl_min int(4) COMMENT                   '箱线最小值',
      bl_mid int(4) COMMENT                   '箱线中位数',
      bl_up_qutr int(4) COMMENT               '箱线上1/4',
      bl_down_qutr int(4) COMMENT             '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='电池冷却水入口温度区间时长分布整体区域';
   ```
+ 车辆充电起始SOC区间分布
    ```sql
    create table if not exists charge_ssocr_dis_summary(
      year varchar(2) NOT NULL COMMENT       '年份',
      month varchar(2) NOT NULL COMMENT      '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT    '区域',
      car_type varchar(10) NOT NULL COMMENT  '车型',
      car_usage varchar(10) NOT NULL COMMENT '用途',
      soc_range tinyint(1) default 0 COMMENT 'SOC区间:0-[0~10],1-[10~20],2-[20~30]...9-[90~100]',
      charge_cnt int(4) default 0 COMMENT    '充电次数',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆充电起始SOC区间分布';
    ```
+ 车辆SOC区间充电量/行驶里程/充电速率分布
    ```sql
    create table if not exists soc_eq_mile_rate_summary(
      year varchar(2) NOT NULL COMMENT          '年份',
      month varchar(2) NOT NULL COMMENT         '月份：1-1月，1-2月... 12-12月',
      region varchar(20) NOT NULL COMMENT       '区域',
      car_type varchar(10) NOT NULL COMMENT     '车型',
      car_usage varchar(10) NOT NULL COMMENT    '用途',
      soc_range tinyint(1) default 0 COMMENT    'SOC区间',
      eq_rate_mile tinyint(1) default 0 COMMENT '0-SOC区间里程、1-充电量、2-充电速率',
      bl_max int(4) COMMENT                     '箱线最大值',
      bl_min int(4) COMMENT                     '箱线最小值',
      bl_mid int(4) COMMENT                     '箱线中位数',
      bl_up_qutr int(4) COMMENT                 '箱线上1/4',
      bl_down_qutr int(4) COMMENT               '箱线下1/4',
      key idx_region (region),
      key idx_car_type (car_type),
      key idx_car_usage (car_usage)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆SOC区间充电量/行驶里程/充电速率分布整体区域';
    ```
## 电池分析-单车
+ 行程电池起始/最高/低温度分布
    ```sql
    create table if not exists bat_temp_dis(
      year varchar(2) NOT NULL COMMENT              '年份',
      month varchar(2) NOT NULL COMMENT             '月份：1-1月，1-2月，... 12-12月',
      vin varchar(17) COMMENT                       '车架号',
      trip_status char(1) NOT NULL COMMENT          '行程类型：0-驾驶,1-充电,2-休眠6小时',
      trip_st_all_flag tinyint(1) default 0 COMMENT '0-起始,1-所有',
      temp_hl_flag tinyint(1) default 0 COMMENT     '0-最高温度,1-最低温度',
      bl_max int(3) COMMENT                         '箱线最大值',
      bl_min int(3) COMMENT                         '箱线最小值',
      bl_mid int(3) COMMENT                         '箱线中位数',
      bl_up_qutr int(3) COMMENT                     '箱线上1/4',
      bl_down_qutr int(3) COMMENT                   '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程最高/低/起始温度分布单车';
    ```
+ 行程电池温差(7、15)/大于45°时长占比分布单
    ```sql
    create table if not exists bat_temp_perd_dis(
      year varchar(2) NOT NULL COMMENT           '年份',
      month varchar(2) NOT NULL COMMENT          '月份：1-1月，1-2月... 12-12月',
      vin varchar(17) COMMENT                    '车架号',
      trip_status char(1) NOT NULL COMMENT       '行程类型：0-驾驶,1-快充,2-慢充',
      temp_dif_flag tinyint(1) default 0 COMMENT '0-温差大于7,1-温差大于15,2-最高温度大于45',
      bl_max int(3) COMMENT                      '箱线最大值',
      bl_min int(3) COMMENT                      '箱线最小值',
      bl_mid int(3) COMMENT                      '箱线中位数',
      bl_up_qutr int(3) COMMENT                  '箱线上1/4',
      bl_down_qutr int(3) COMMENT                '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程电池温差(7、15)/大于45°时长占比分布单车';
    ```
+ 行程电池温差/压差数值分布
    ```sql
    create table if not exists temp_vol_dis_dis(
      year varchar(2) NOT NULL COMMENT              '年份',
      month varchar(2) NOT NULL COMMENT             '月份：1-1月，1-2月... 12-12月',  
      vin varchar(17) COMMENT                       '车架号',
      trip_type char(1) NOT NULL COMMENT            '行程类型：0-驾驶 1-快充 2-慢充',
      bat_temp_vol_dif tinyint(1) default 0 COMMENT '0-温差 1-压差',
      bl_max int(3) COMMENT                         '箱线最大值',
      bl_min int(3) COMMENT                         '箱线最小值',
      bl_mid int(3) COMMENT                         '箱线中位数',
      bl_up_qutr int(3) COMMENT                     '箱线上1/4',
      bl_down_qutr int(3) COMMENT                   '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程电池温差/压差数值分布单车';
    ```
+ 行程各温度/电压区间时长分布
    ```sql
    create table if not exists temp_vol_perd(
      year varchar(2) NOT NULL COMMENT           '年份',
      month varchar(2) NOT NULL COMMENT          '月份：1-1月，1-2月，... 12-12月',
      vin varchar(17) COMMENT                    '车架号',
      trip_type char(1) NOT NULL COMMENT         '行程类型：0-驾驶 1-快充 2-慢充',
      temp_vol_flag tinyint(1) default 0 COMMENT '0-温度  1-电压',
      temp_range tinyint(1) default 0 COMMENT    '温度区间范围：0-[-40~-20] 1-[-20~0] 2-[0~20] 3-[20~40] 4-[40~60];电压区间范围：0-[0~20] 1-[20~40] 2-[4~60] 3-[60~80] 4-[80~100]',
      bl_max int(4) COMMENT                      '箱线最大值',
      bl_min int(4) COMMENT                      '箱线最小值',
      bl_mid int(4) COMMENT                      '箱线中位数',
      bl_up_qutr int(4) COMMENT                  '箱线上1/4',
      bl_down_qutr int(4) COMMENT                '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程各温度/电压区间时长分布单车';
    ```
+ 行程电池冷却水入口和出口温差数值分布
    ```sql
    create table if not exists bat_watrio_temp_dif(
      year varchar(2) NOT NULL COMMENT   '年份',
      month varchar(2) NOT NULL COMMENT  '月份：1-1月，1-2月... 12-12月',  
      vin varchar(17) COMMENT            '车架号',
      trip_type char(1) NOT NULL COMMENT '行程类型：0-快冷 1-慢冷 2-电池加热',
      bl_max int(4) COMMENT              '箱线最大值',
      bl_min int(4) COMMENT              '箱线最小值',
      bl_mid int(4) COMMENT              '箱线中位数',
      bl_up_qutr int(4) COMMENT          '箱线上1/4',
      bl_down_qutr int(4) COMMENT        '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='行程电池冷却水入口和出口温差数值分布单车';
   ```
+ 电池冷却水入口温度区间时长分布
    ```sql
    create table if not exists bat_watri_temp_range(
      year varchar(2) NOT NULL COMMENT        '年份',
      month varchar(2) NOT NULL COMMENT       '月份：1-1月，1-2月... 12-12月',  
      vin varchar(17) COMMENT                 '车架号',
      trip_type char(1) NOT NULL COMMENT      '行程类型：0-快冷 1-慢冷 2-电池加热',
      temp_range tinyint(1) default 0 COMMENT '温度区间:温度区间:0-[-40~-20],1-[-20~0],2-[0~20],3-[20~40]',
      bl_max int(4) COMMENT                   '箱线最大值',
      bl_min int(4) COMMENT                   '箱线最小值',
      bl_mid int(4) COMMENT                   '箱线中位数',
      bl_up_qutr int(4) COMMENT               '箱线上1/4',
      bl_down_qutr int(4) COMMENT             '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='电池冷却水入口温度区间时长分布单车';
   ```
+ 车辆充电起始SOC区间分布
    ```sql
    create table if not exists charge_ssocr_dis(
      year varchar(2) NOT NULL COMMENT       '年份',
      month varchar(2) NOT NULL COMMENT      '月份：1-1月，1-2月... 12-12月',  
      vin varchar(17) COMMENT                '车架号',
      soc_range tinyint(1) default 0 COMMENT 'SOC区间范围：0-[0~20],1-[20~40],2-[40~60],3-[60~80]',
      charge_cnt int(4) default 0 COMMENT    '充电次数',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆SOC区间行驶里程分布单车';
    ```
+ 车辆SOC区间充电量/行驶里程/充电速率分布
    ```sql
    create table if not exists soc_eq_mile_rate(
      year varchar(2) NOT NULL COMMENT          '年份',
      month varchar(2) NOT NULL COMMENT         '月份：1-1月，1-2月，... 12-12月',  
      vin varchar(17) COMMENT                   '车架号',
      soc_range tinyint(1) default 0 COMMENT    'SOC区间范围：0-[0~10],1-[10~20],2-[20~30]...9-[90~100]',
      eq_rate_mile tinyint(1) default 0 COMMENT '0-里程、1-充电量、2-充电速率',
      bl_max int(4) COMMENT                     '箱线最大值',
      bl_min int(4) COMMENT                     '箱线最小值',
      bl_mid int(4) COMMENT                     '箱线中位数',
      bl_up_qutr int(4) COMMENT                 '箱线上1/4',
      bl_down_qutr int(4) COMMENT               '箱线下1/4',
      primary key (vin)
    ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆SOC区间充电量分布整体区域';
    ```

## 其它零部件分析-整体区域

- 零部件温度分布
- 电机系统水泵占空比分布
- 母线电压分布

```sql
create table parts_summary (
  year varchar(4) NOT NULL COMMENT                     '年份',
  month varchar(2) NOT NULL COMMENT                    '月份：1-1月，1-2月，... 12-12月', 
  vintype tinyint(1) NOT NULL COMMENT                  '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  region varchar(20) NOT NULL COMMENT                  '区域： 含所有区域',
  parts_type tinyint(1) NOT NULL COMMENT               '0-驱动电机, 1-驱动电机控制器, 2-发动机, 3-电枢系统冷却水, 4-电机系统水泵,5-母线',
  usage tinyint(1) NOT NULL COMMENT                    '车辆用途：0-出租车，1-私家车',
  value_type tinyint(1) NOT NULL COMMENT               '指标类型：0-温度, 1-流量, 2-电压',
  max_temp double(16,2) default 0.0 COMMENT            '最大值',
  min_temp double(16,2) default 0.0 COMMENT            '最小值',
  quarter_temp double(16,2) default 0.0 COMMENT        '四分之一分位值',
  three_quarters_temp double(16,2) default 0.0 COMMENT '四分之三分位值',
  median_temp double(16,2) default 0.00  COMMENT       '中位数',
  UNIQUE KEY `unique_parts_temp_summary` (`year`, `month`, `vintype`, `region`, `parts_type`, `usage`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_region` (`region`),
  KEY `idx_parts_type` (`parts_type`),
  KEY `idx_usage` (`usgae`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='零部件温度,电机系统水泵占空比,母线电压分布表（整体区域）';
```

- DC-DC在不同buck模式温度分布
- DC-DC工作状态母线电压分布

```sql
create table dcdc_summary (
  year varchar(4) NOT NULL COMMENT                     '年份',
  month varchar(2) NOT NULL COMMENT                    '月份：1-1月，1-2月，... 12-12月', 
  vintype tinyint(1) NOT NULL COMMENT                  '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  region varchar(20) NOT NULL COMMENT                  '区域： 含所有区域',
  buck_type tinyint(1) NOT NULL COMMENT                '0-buck模式, 1-非buck模式',
  usage tinyint(1) NOT NULL COMMENT                    '车辆用途：0-出租车，1-私家车',
  value_type tinyint(1) NOT NULL COMMENT               '指标类型：0-温度，1-电压',
  max_temp double(16,2) default 0.0 COMMENT            '最大值',
  min_temp double(16,2) default 0.0 COMMENT            '最小值',
  quarter_temp double(16,2) default 0.0 COMMENT        '四分之一分位值',
  three_quarters_temp double(16,2) default 0.0 COMMENT '四分之三分位值',
  median_temp double(16,2) default 0.00  COMMENT       '中位数',
  UNIQUE KEY `unique_parts_temp_summary` (`year`, `month`, `vintype`, `region`, `buck_type`, `usage`, `value_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_region` (`region`),
  KEY `idx_buck_type` (`buck_type`),
  KEY `idx_usage` (`usgae`),
  KEY `idx_value_type` (`value_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='DC-DC在不同buck模式温度/母线电压分布（整体区域）';
```

- PTC/ECP/电池加热器状态分布

```sql
create table ptc_ecp_summary (
  year varchar(4) NOT NULL COMMENT            '年份',
  month varchar(2) NOT NULL COMMENT           '月份：1-1月，1-2月，... 12-12月',   
  vintype tinyint(1) NOT NULL COMMENT         '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  region varchar(20) NOT NULL COMMENT         '区域： 含所有区域',
  parts_type tinyint(1) NOT NULL COMMENT      '零件类型：0-PTC, 1-ECP, 2-电池加热器',
  launch_count int(11) default 0 COMMENT      '启动次数',
  duration int(11) default 0 COMMENT          '持续时长',
  power_consumption int(11) default 0 COMMENT '累积能耗',
  UNIQUE KEY `vin_yaer_month` (`year`, `month`, `vintype`, `region`, `parts_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_parts_type` (`parts_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PTC/ECP/电池加热器状态分布表（整体区域）';
```

- 三通阀/快冷/慢冷状态分布

```sql
create table 3to2_valve_summary (
  year varchar(4) NOT NULL COMMENT            '年份',
  month varchar(2) NOT NULL COMMENT           '月份：1-1月，1-2月，... 12-12月',   
  vintype tinyint(1) NOT NULL COMMENT         '车型：0-所有车型, 1-A51, 2-A2A, 3-A75, 4-A5H',
  region varchar(20) NOT NULL COMMENT         '区域： 含所有区域',
  parts_type tinyint(1) NOT NULL COMMENT      '零件类型：0-三通阀, 1-快冷, 2-慢冷',
  launch_count int(11) default 0 COMMENT      '启动次数',
  duration int(11) default 0 COMMENT          '持续时长',
  UNIQUE KEY `vin_yaer_month` (`year`, `month`, `vintype`, `region`, `parts_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vintype` (`vintype`),
  KEY `idx_parts_type` (`parts_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三通阀/快冷/慢冷状态分布表（整体区域）';
```

## 其他零部件分析-单车

- 零部件温度分布
- 电机系统水泵占空比分布
- 母线电压分布

```sql
create table parts (
  year varchar(4) NOT NULL COMMENT                     '年份',
  month varchar(2) NOT NULL COMMENT                    '月份：1-1月，1-2月，... 12-12月', 
  vin varchar(17) NOT NULL COMMENT                     '车架号',
  parts_type tinyint(1) NOT NULL COMMENT               '0-驱动电机, 1-驱动电机控制器, 2-发动机, 3-电枢系统冷却水, 4-电机系统水泵,5-母线',
  value_type tinyint(1) NOT NULL COMMENT               '指标类型：0-温度, 1-流量, 2-电压',
  max_temp double(16,2) default 0.0 COMMENT            '最大值',
  min_temp double(16,2) default 0.0 COMMENT            '最小值',
  quarter_temp double(16,2) default 0.0 COMMENT        '四分之一分位值',
  three_quarters_temp double(16,2) default 0.0 COMMENT '四分之三分位值',
  median_temp double(16,2) default 0.00  COMMENT       '中位数',
  UNIQUE KEY `unique_parts` (`year`, `month`, `vin`, `parts_type`, `value_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vin` (`vin`),
  KEY `idx_parts_type` (`parts_type`),
  KEY `idx_value_type` (`value_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='零部件温度,电机系统水泵占空比,母线电压分布表（单车）';
```

- 驱动电机转速与扭矩关系分布
- 发电机转速与扭矩关系分布
- 发动机转速与扭矩关系分布

```sql
create table speed_torque_relation (
  day datetime NOT NULL COMMENT          '出行日期,yyyy-MM-dd 格式',
  vin varchar(17) NOT NULL COMMENT       '车架号',
  motor_type tinyint(1) NOT NULL COMMENT '电机类型：0-驱动电机, 1-发电机, 2-发动机',
  spd_range tinyint(1) NOT NULL COMMENT  '速度区间：0-0..500,1-500..1000,2-1000..1500,3-1500..2000,4-2000..2500,5-2500..3000,6-3000..3500,7-3500..4000,8-4000..4500,9-4500..5000',
  torq_range tinyint(1) NOT NULL COMMENT '扭矩区间：0-0..50,1-50..100,2-100..150,3-150..200,4-200..250,5-250..300,6-300..350',
  count bigint(20) default 0 COMMENT     '个数',
  UNIQUE KEY `unique_speed_torque_relation` (`day`, `vin`, `motor_type`),
  KEY `idx_day` (`day`),
  KEY `idx_vin` (`vin`),
  KEY `idx_motor_type` (`motor_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='驱动电机/发电机/发动机转速与扭矩关系分布表（单车）';
```

- 不同温度/电压/区间转速扭矩关系分布

**TBA**

```sql
create table speed_torque_temp_relation (
  day datetime NOT NULL COMMENT          '出行日期,yyyy-MM-dd 格式',
  vin varchar(17) NOT NULL COMMENT       '车架号',
  motor_type tinyint(1) NOT NULL COMMENT '电机类型：0-驱动电机, 1-发电机, 2-发动机',
  spd_range tinyint(1) NOT NULL COMMENT  '速度区间：0-0..500,1-500..1000,2-1000..1500,3-1500..2000,4-2000..2500,5-2500..3000,6-3000..3500,7-3500..4000,8-4000..4500,9-4500..5000',
  torq_range tinyint(1) NOT NULL COMMENT '扭矩区间：0-0..50,1-50..100,2-100..150,3-150..200,4-200..250,5-250..300,6-300..350',
  temp_range tinyint(1) NOT NULL COMMENT '温度区间：待定',
  count bigint(20) default 0 COMMENT     '个数',
  UNIQUE KEY `unique_speed_torque_temp_relation` (`day`, `vin`, `motor_type`, `temp_range`),
  KEY `idx_day` (`day`),
  KEY `idx_vin` (`vin`),
  KEY `idx_motor_type` (`motor_type`),
  KEY `idx_temp_range` (`temp_range`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不同温度/电压/区间转速扭矩关系分布分布表（单车）';
```

- PTC/ECP/电池加热器状态分布

```sql
create table ptc_ecp ( 
  year varchar(4) NOT NULL COMMENT            '年份',
  month varchar(2) NOT NULL COMMENT           '月份：1-1月，1-2月，... 12-12月',   
  vin varchar(17) NOT NULL COMMENT            '车架号', 
  parts_type tinyint(1) NOT NULL COMMENT      '零件类型：0-PTC, 1-ECP, 2-电池加热器',
  launch_count int(11) default 0 COMMENT      '启动次数',
  duration int(11) default 0 COMMENT          '持续时长',
  power_consumption int(11) default 0 COMMENT '累积能耗',
  UNIQUE KEY `unique_ptc_ecp` (`year`, `month`, `vin`, `parts_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vin` (`vin`),
  KEY `idx_parts_type` (`parts_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PTC/ECP/电池加热器状态分布表（单车）';
```

- 三通阀/快冷/慢冷状态分布

```sql
create table 3to2_valve (
  year varchar(4) NOT NULL COMMENT            '年份',
  month varchar(2) NOT NULL COMMENT           '月份：1-1月，1-2月，... 12-12月',   
  vin varchar(17) NOT NULL COMMENT            '车架号', 
  parts_type tinyint(1) NOT NULL COMMENT      '零件类型：0-三通阀, 1-快冷, 2-慢冷',
  launch_count int(11) default 0 COMMENT      '启动次数',
  duration int(11) default 0 COMMENT          '持续时长',
  UNIQUE KEY `unique_3to2_valve` (`year`, `month`, `vin`, `parts_type`),
  KEY `idx_year` (`year`),
  KEY `idx_month` (`month`),
  KEY `idx_vintype` (`vin`),
  KEY `idx_parts_type` (`parts_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三通阀/快冷/慢冷状态分布表（单车）';
```


# 故障分析
## 故障统计-区域整体

+ 区域分布
+ 车型占比
+ 等级占比
+ 类型占比
+ 车辆占比
+ 故障数Top15
```sql
create table if not exists alm_ana_statis_all(
    year varchar(2) NOT NULL COMMENT         '年份',
    month varchar(2) NOT NULL COMMENT        '月份：1-1月，1-2月... 12-12月',
    region_name varchar(20) NOT NULL COMMENT '区域名称',
    car_type varchar(10) NOT NULL COMMENT    '车型',
    car_usage varchar(10) NOT NULL COMMENT   '用途',
    alm_lvl char(1) NOT NULL COMMENT         '故障等级',
    alm_type varchar(10) NOT NULL COMMENT    '故障类型',
    alm_name varchar(20) NOT NULL COMMENT    '故障名称',
    count int(11) default 0 COMMENT          '故障数',
    key idx_region (region_name),
    key idx_car_type (car_type),
    key idx_car_usage (car_usage)
   ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='故障分析统计表整体区域';
```
+ 平均无故障间隔里程
```sql
create table if not exists avg_noalm_inter_all(
    region_name varchar(20) NOT NULL COMMENT   '区域名称',
    car_type varchar(10) NOT NULL COMMENT      '车型',
    car_usage varchar(10) NOT NULL COMMENT     '用途',
    ave_mile double(16,2) default 0.00 COMMENT '平均里程',
   ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='平均无故障间隔里程整体区域';
```

## 故障统计-单车

+ 故障等级占比
+ 故障类型占比
+ 故障top15
+ 故障等级时序图
+ 故障类型时序图
```sql
create table if not exists alm_ana_statis(
    year varchar(2) NOT NULL COMMENT      '年份',
    month varchar(2) NOT NULL COMMENT     '月份：1-1月，1-2月，... 12-12月',
    vin varchar(17) NOT NULL COMMENT      '车架号',
    alm_lvl char(1) NOT NULL COMMENT      '故障等级',
    alm_type varchar(10) NOT NULL COMMENT '故障类型',
    alm_name varchar(20) NOT NULL COMMENT '故障名称',
    count int(11) default 0 COMMENT '故障数',
    primary key (vin),
    key idx_alm_lvl (alm_lvl),
    key idx_alm_type (alm_type)
   ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='故障分析统计表单车'
```
+ 平均无故障间隔里程
```sql
create table if not exists avg_noalm_inter (
    day datetime NOT NULL COMMENT              '出行日期,yyyy-MM-dd 格式',
    vin varchar(17) primary key COMMENT        '车架号',
    ave_mile double(16,2) default 0.00 COMMENT '平均里程',
   ) engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='平均无故障间隔里程单车'
```

- 全国每日所有车辆所有故障统计


```sql
create table real_time_summary (
  attribute tinyint(1) NOT NULL COMMENT      '0-events, 1-其它',
  attribute_num bigint(20) default 0 COMMENT '数量',
  UNIQUE KEY `unique_real_time_summary` (`attribute`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日所有车辆所有故障（或其它指标）汇总表';
```