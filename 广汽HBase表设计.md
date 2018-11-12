[TOC]

## 数据的接入

- CDH 5.13， Spark 2.3.0  
- Kafka 0.10
- HBase  1.2.0  

## HBase 表

HBase 表字段统一使用小写，带下划线形式。

### 1）原始数据表

原始数据保存在 `can_signal` HBase 表中。分为俩个列簇：**info** 和 **content**。 HBase 表结构如下：

```
can_signal： create 'can_signal',{NAME =>'info', COMPRESSION => 'LZ4'},{NAME =>'content', COMPRESSION => 'LZ4'},SPLITS =>['00','02','04','06', '08', '10', '12', '14', '16','18','20','22','24','26','28', '30', '32', '34', '36', '38','40', '42', '44', '46', '48','50','52','54','56','58', '60', '62','64','66','68','70','72', '74','76','78','80', '82', '84', '86', '88','90', '92', '94', '96', '98']
```

info 用于存储需要经常查询的字段, content 用于存储不常用的字段：

|列簇|字段|数据类型|说明|
|:----:|:-------------------------------------|:----|:------------|
| info | vin                                  |     |	车架号     |
| info | vintype                              |     |	车辆类型	|
| info | ts                                   |     |	发生时间	|
| info | veh_st                               |     |	车辆状态	|
| info | veh_chargest                         |     |	充电状态	|
| info | veh_runmode                          |     |	运行模式	|
| info | veh_spd                              |     |	速度	   |
| info | veh_odo                              |     |	累计里程	|
| info | veh_volt                             |     |	总电压      |
| info | veh_curr                             |     |	总电流      |
| info | veh_soc                              |     |	SOC	       |
| info | veh_dcdcst                           |     |	DC_DC状态	|
| info | veh_gear                             |     |	档位	    |
| info | veh_insulation                       |     |	绝缘电阻	   |
| info | veh_pedal_deep                       |     |	加速踏板行程值	|
| info | veh_pedalst                          |     |	制动踏板状态	|
| info | dm_seq                               |     |	驱动电机序号	|
| info | dm_st                                |     |	驱动电机状态	|
| info | dm_ctl_temp                          |     |	驱动电机控制器温度	|
| info | dm_spd                               |     |	驱动电机转速	|
| info | dm_torq                              |     |	驱动电机转矩	|
| info | dm_temp                              |     |	驱动电机温度	|
| info | dm_ctl_volt                          |     |	电机控制器输入电压	|
| info | dm_ctl_dc_curr                       |     |	电机控制器直流母线电流	|
| info | eng_st                               |     |	发动机状态            |
| info | eng_spd                              |     |	曲轴转速	          |
| info | eng_consumption                      |     |	燃料消耗率            |
| info | loc_st                               |     |	定位状态	          |
| info | loc_lon84                            |     |	经度	             |
| info | loc_lat84                            |     |	纬度	             |
| info | data_batt_subsys_volt_highest_seq    |     |	最高电压电池子系统号	|
| info | data_batt_sc_volt_highest_seq        |     |	最高电压电池单体代号	|
| info | data_batt_sc_volt_highest            |     |	电池单体电压最高值	    |
| info | data_batt_subsys_volt_lowest_seq     |     |	最低电压电池子系统号	|
| info | data_batt_sc_volt_lowest_seq         |     |	最低电压电池单体代号	|
| info | data_batt_sc_volt_lowest             |     |	电池单体电压最低值	|
| info | data_batt_subsys_temp_highest_seq    |     |	最高温度子系统号	|
| info | data_batt_temp_probe_highest_seq     |     |	最高温度探针序号	|
| info | data_batt_temp_highest               |     |	最高温度值	       |
| info | data_batt_subsys_temp_lowest_seq     |     |	最低温度子系统号	|
| info | data_batt_temp_probe_lowest_seq      |     |	最低温度探针序号	|
| info | data_batt_temp_lowestest             |     |	最低温度值      	|
| info | alm_lvl_higest                       |     |	最高报警等级	|
| info | alm_common_flag                      |     |	衰退报警？	|
| info | alm_common_temp_diff                 |     |	温度差异报警	|
| info | alm_common_temp_high                 |     |	电池高温报警	|
| info | alm_common_esd_high                  |     |	车载储能装置类型过压报警	|
| info | alm_common_esd_low                   |     |	车载储能装置类型欠压报警	|
| info | alm_common_soc_low                   |     |	SOC低报警	|
| info | alm_common_sc_high                   |     |	单体电池过压报警	|
| info | alm_common_sc_low                    |     |	单体电池欠压报警	|
| info | alm_common_soc_high                  |     |	SOC过高报警	|
| info | alm_common_soc_hop                   |     |	SOC跳变报警	|
| info | alm_common_esd_unmatch               |     |	可充电储能系统不匹配报警	|
| info | alm_common_sc_consistency            |     |	电池单体一致性差报警	|
| info | alm_common_insulation                |     |	绝缘报警	|
| info | alm_common_dcdc_temp                 |     |	DC~DC温度报警	|
| info | alm_common_brk                       |     |	制动系统报警	|
| info | alm_common_dcdc_st                   |     |	DC~DC状态报警	|
| info | alm_common_dmc_temp                  |     |	驱动电机控制器温度报警	|
| info | alm_common_hvil_st                   |     |	高压互锁状态报警	|
| info | alm_common_dm_temp                   |     |	驱动电机温度报警	|
| info | alm_common_esd_charge_over           |     |	车载储能装置类型过充	|
| info | alm_esd_cnt                          |     |	可充电储能装置故障总数N1	|
| info | alm_esd_list                         |     |	可充电储能装置故障代码列表	|
| info | alm_dm_cnt                           |     |	驱动电机故障总数N2	|
| info | alm_dm_list                          |     |	驱动电机故障代码列表	|
| info | alm_eng_cnt                          |     |	发动机故障总数N3	|
| info | alm_eng_list                         |     |	发动机故障代码列表	|
| info | alm_others_cnt                       |     |	其他故障总数N4	|
| info | alm_others_list                      |     |	其他故障代码列表	|
| info | esd_volt_subsys_seq                  |     |	可充电储能子系统号	|
| info | esd_volt                             |     |	可充电储能装置电压	|
| info | esd_curr                             |     |	可充电储能装置电流	|
| info | esd_sc_cnt                           |     |	单体电池总数	|
| info | esd_frame_start                      |     |	本帧起始电池序号	|
| info | esd_frame_sc_cnt                     |     |	本帧单体电池总数	|
| info | esd_frame_sc_list                    |     |	单体电池电压	|
| info | esd_temp_probe_list                  |     |	可充电储能子系统各温度探针测到的温度值	|
|cold  |vcu_brk_ped_st	                      |	    | 制动踏板深度	|
|cold  |vcu_ems_acc_pedal_act_pst	          |	    | 加速踏板深度	|
|cold  |dcu_rot_spd_act	                      |	    | 驱动电机转速	|
|cold  |dcu_torq_act	                      |	    | 驱动电机扭矩	|
|cold  |bcm_key_st	                          |	    | 车辆钥匙信号	|
|cold  |vcu_veh_elc_consp_accum_avg	          |	    | 累积平均电耗	|
|cold  |vcu_veh_elc_consp_avg	              |	    | 瞬时平均电耗	|
|cold  |vcu_elc_sys_err	                      |	    | VCU故障灯提示	|
|cold  |vcu_gear_lvl_disp	                  |	    | 换挡手柄位置	|
|cold  |vcu_crnt_gear_lvl	                  |	    | 实际档位	   |
|cold  |vcu_veh_drv_mod	                      |	    | 车辆运行模式	|
|cold  |vcu_tgw_em_valve_en	                  |	    | 电磁阀状态	   |
|cold  |vcu_tgw_3to2_valve_en	              |	    | 三通阀状态	           |
|cold  |vcu_tgw_water_pump1_en	              |	    | 电池冷却回路水泵使能状态	|
|cold  |vcu_tgw_water_pump1_spd_set	          |	    | 电池冷却回路水泵占空比	|
|cold  |vcu_water_temp	                      |	    | 电驱系统冷却水温度	|
|cold  |vcu_tgw_water_pump2_spd_set	          |	    | 电驱系统水泵占空比	|
|cold  |dcu_mot_temp_act	                  |	    | 驱动电机温度	       |
|cold  |dcu_volt_act	                      |	    | 电机控制器输入电压	|
|cold  |dcu_curr_act	                      |	    | 电机控制器直流母线电流 |
|cold  |dcu_invt_temp_act	                  |	    | 驱动电机控制器温度	|
|cold  |dcu_err_lvl	                          |	    | 驱动电机故障等级	    |
|cold  |dcu_fault_lamp_ind	                  |	    | 驱动电机故障灯提示	|
|cold  |tgw_exter_temp	                      |	    | 外温          	|
|cold  |tgw_batt_temp	                      |	    | 电池冷却水入口温度	|
|cold  |tgw_batt_temp	                      |	    | 电池却水出口温度	  |
|cold  |tgw_hvh_pwr_act	                      |	    | 电池加热器功率	  |
|cold  |tgw_fault_lamp_ind	                  |	    | TGW故障灯信号	     |
|cold  |dcdc_temp_act	                      |	    | DCDC温度	        |
|cold  |dcdc_volt_hv_act	                  |	    | DCDC母线电压       |
|cold  |dcdc_curr_hv_act	                  |	    | DCDC输入电流       |
|cold  |dcdc_curr_lv_act	                  |	    | DCDC输出电流       |
|cold  |dcdc_volt_lv_act	                  |	    | DCDC输出电压       |
|cold  |dcdc_err_lvl	                      |	    | DCDC故障等级       |
|cold  |dcdc_fault_lamp_ind	                  |	    | DCDC故障灯提示	 |
|cold  |bms_err_lvl	                          |	    | 电池故障等级	      |
|cold  |bms_fault_lamp_ind	                  |	    | 电池故障灯提示	   |
|cold  |bms_batt_soc	                      |	    | 电池SOC	         |
|cold  |bms_batt_volt	                      |	    | 电池电压            |
|cold  |bms_batt_curr	                      |	    | 电池电流            |
|cold  |bms_insl_res	                      |	    | 绝缘电阻            |
|cold  |bms_cell_volt_max	                  |	    | 电池单体电压最高值	|
|cold  |bms_cell_volt_min	                  |	    | 电池单体电压最低值	|
|cold  |bms_cell_temp_max	                  |	    | 最高温度值	       |
|cold  |bms_cell_temp_min	                  |	    | 最低温度值	       |
|cold  |hvac_pwr_consmp	                      |	    | PTC功率	          |
|cold  |hvac_pwr_consump	                  |	    | ECP功率	          |
|cold  |hvac_fault      	                  |	    | 压缩机不可用状态	    |
     

### 2）行程表


#### 2.1）驾驶行程表

- HBase 表结构

驾驶行程表的 info 列簇如下：

```shell
create 'trip_signal',{NAME =>'info', COMPRESSION => 'LZ4'},SPLITS => ['0','1', '2', '3', '4','5', '6', '7', '8', '9']
```

行程中需要很多字段。

驾驶行程表有一个列簇：

|列簇|字段|数据类型|说明|
|:--:|:---------------------|:----------|:-----------|
|info|vin                   | String    | 车架号      |
|info|trip_start_time       | Long      | 行程开始时间 |
|info|trip_end_time         | Long      | 行程结束时间 |
|info|trip_duration         | Long      | 行驶时长    |
|info|start_mileage         | Double    | 开始里程    |
|info|end_mileage           | Double    | 结束里程    |
|info|trip_distance         | Double    | 行驶里程    |
|info|start_soc             | Int       | 起始 SOC   |
|info|end_soc               | Int       | 结束 SOC   |
|info|soc_consumption       | Double    | SOC 消耗   |
|info|start_longitude       | Double    | 开始经度   |
|info|end_longitude         | Double    | 结束经度   |
|info|start_latitude        | Double    | 开始纬度   |
|info|end_latitude          | Double    | 结束纬度   |  
|info|low_speed_num         | Long      | 低速次数   |
|info|medium_speed_num      | Long      | 中速次数  |
|info|high_speed_num        | Long      | 高速次数  |
|info|low_speed_soc         | Double    | 低速SOC  |
|info|medium_speed_soc      | Double    | 中速SOC  |
|info|high_speed_soc        | Double    | 高速SOC  |
|info|low_speed_mileage     | Double    | 低速里程  | 
|info|medium_speed_mileage  | Double    | 中速里程  |
|info|high_speed_mileage    | Double    | 高速里程  |
|info|average_speed         | Double    | 平均速度  |
|info|max_temp              | Double    | 最高温度  |
|info|min_temp              | Double    | 最低温度  |
|info|start_temp            | Int       |**车辆启动时的起始温度**|
|info|dcdc_volt_hvact       | Int       |**母线电压**| 
|info|vcu_water_temp2       | Int       |**驱动电机冷却水温度**|
|info|trip_status           | Int       | 行程状态   |
|info|trip_max_temp         | Int       | 行程最高温度|
|info|min_moltage           | Int       |最低电压    |
|info|max_voltage           | Int       |最高电压    |
|info|samping_gps           | Int       |取样GPS    |
|info|samping_speed         | Double    |取样速度    |
|info|samping_acc           | Double    |取样加速度  |
|info|samping_temp          | Double    |取样温度   |  
|info|start_first_frame_temp| Int       |**车辆启动时的电池第一帧温度**|   
|info|harsh_acc             | Int       |急加速次数 |
|info|harsh_slowdown        | Int       |急减速次数 |
|info|harsh_stop            | Int       |急停次数   |

#### 2.2）充电行程表

- HBase 表结构

```shell
create 'charge_trip',{NAME =>'info', COMPRESSION => 'LZ4'},SPLITS => ['0','1', '2', '3', '4','5', '6', '7', '8', '9']
```

充电行程表的 info 列簇如下：

|列簇|字段|数据类型|说明|
|:--:|:---------------------|:-------|:-------|
|info|vin                   | String  |车架号   |
|info|vintype               | String  |车辆类型 |
|info|ts                    | Long    |事件发生时间|
|info|charge_start_temp     | Int     |**车辆开始充电时的起始温度**|
|info|charge_start_time     | Long    | 充电开始时间 |​
|info|charge_end_time       | Long    | 充电结束时间 |​
|info|charge_duration       | Long    | 充电时长 |​
|info|charge_start_soc      | Double  | 充电起始电量 |​
|info|charge_end_soc        | Double  | 充电结束电量 |​
|info|charge_soc            | Double  | 充电电量 |​
|info|longitude             | Double  | 经度 |​
|info|latitude              | Double  | 纬度 |​
|info|charge_voltage        | Double  | 充电电压 |​
|info|charge_current        | Double  | 充电电流 |​
|info|charge_power          | Double  | 充电功率 |​
|info|charge_type           | Int     | 充电类型 |​
|info|charge_min_temp       | Int     | 电池最低温度 |
|info|charge_max_temp       | Int     | 电池最高温度 |
|info|charge_status         | Int     | 充电状态    |
|info|start_mileage         | Long     | 开始里程数|


### 3）latest 表

存储最后一条数据。（最后一条数据有哪些）

```shell
create 'latest',{NAME =>'info', COMPRESSION => 'LZ4'},SPLITS => ['0','1', '2', '3', '4','5', '6', '7', '8', '9']
```

|列簇|字段|数据类型|说明| 
|:--:|:-----------------|:-------|:------------------------|
|info|vin               | String | 车架号                   |
|info|bcs_vehspd        | Long   | 车速                    |
|info|veh_soc           | Int    | SOC(国标)               |
|info|bms_batt_soc      | Int    | 电池SOC（企标）          |
|info|veh_volt          | Int    | 总电压                  |
|info|veh_pedalst       | Int    | 制动踏板状态             |
|info|veh_gear          | Int    | 档位                    |
|info|dcdc_status       | Int    | DCDC状态             |
|info|veh_curr          | Int    | 总电流               |
|info|veh_pedalst       | Int    | 制动踏板状态          |
|info|dm_st             | Int    | 驱动电机状态          |
|info|dm_spd            | Int    | 驱动电机转速          |
|info|dcu_rot_spd_act   | Int    | 驱动电机转速          |
|info|dm_temp           | Int    | 驱动电机温度          |
|info|dcu_mot_temp_act	| Int    | 驱动电机温度          |
|info|dm_ctl_volt       | Int    | 电机控制器输入电压(国标)|
|info|dcu_volt_act      | Int    | 电机控制器输入电压(企标)|
|info|Unkonwn!          | Int    | 电机控制器输入电流     |
|info|dm_ctl_temp       | Int    | 驱动电机控制器温度     |
|info|dcu_invt_temp_act | Int    | 驱动电机控制器温度     |
|info|dcu_torq_act      | Int    | 驱动电机扭矩          |
|info|longitude         | Double | 经度                 | 
|info|latitude          | Double | 纬度                 |


不同数据格式同一字段可能列的名字不一样，需要自己标准化为一个字段：


|列簇|字段|数据类型|说明| 
|:--:|:-----------------|:-------|:------------------------|
|info|veh_dcdcst        | Int    | DCDC状态(**国标**)       |
|info|DCDC_ModeSt       | Int    | DCDC状态(**企标**A5H-EV) |
|info|IPS_DcdcModeSt    | Int    | DCDC状态(**企标**A75-EV )|

变为：

|列簇|字段|数据类型|说明| 
|:--:|:-----------------|:-------|:------------------------|
|info|dcdc_status       | Int    | DCDC状态                |


同样的字段需要标准化格式。
经度和维度用来确定单车的**地理位置**。

### 4）events 表

增加 events HBase 表，用于三通阀等事件，故障，开关，事件检测，加速度分布。

```shell
create 'events',{NAME =>'info', COMPRESSION => 'LZ4'},SPLITS => ['0','1', '2', '3', '4','5', '6', '7', '8', '9']
```

|列簇|字段|数据类型|说明|
|:---:|:---------------------|:-------|:---------------|
|info | vin                 | String | 车架号           |
|info | event_type          | String | 事件类型           |
|info | event_name          | String | 事件名称 |
|info | event_time          | Long   |事件发生时间 |
|info | event_level         | Int    | 事件等级 |
|info | event_reason        | String | 事件原因 |
|info | latitude            | Double | 纬度 |
|info | longitude           | Double | 经度 |


row key: reverse(vin + event_tpye) + yyyymmdd 日期。

events 表取一天数据的，需要使用二级索引，phoenix？ 根据时间戳，反向查找出某段时间的 row key。（参考威马）


## 问题

- 月累积快冷和慢冷次数和时长分布

慢冷和快冷是哪个零部件, 如何计算慢冷和快冷次数，如何计算慢冷时长和快冷时长。

- 月累积电池加热器启动次数和持续时长分布

电池加热器启动次数是根据哪个字段进行计算的？目前只有 `TGW_HVPTCHVDCCurr`： 电池加热器电流，和 `TGW_HVPTCHVVoltage`： 电池加热器电压。

- 月累积 PTC/ECP 功耗分布

`HVAC_PTCPwrConsmp`	 - PTC功率

`HVAC_ECPPwrConsump` - ECP功率

功耗 - 损耗了多少能量。单位 W。功耗 = 输入功率 - 输出功率。 电压X电流 = 功率X时间 = 功耗。
功率 - 做功的快慢。单位 W

- 月累积PTC/ECP启动次数、持续时长分布

PTC/ECP 启动次数使用哪个字段计算, 持续时长使用哪些字段字段？

- 发电机温度分布

发电机温度使用哪个字段？

电池加热器启动次数使用哪个字段计算？

- 驱动电机冷却水流量

驱动电机冷却水流量如何计算，使用哪个字段？


电池：

休眠6小时：上次停止到下次开始（这期间既没有行程，又没有充电行为）

温差：最高温度 - 最低温度。温差值的分布， 温差 > 15 度时的时长分布：一个行程有很多点（min, max）, 假设这段行程为 60s, 每 10秒 一个点， 有 3 个点的温差 > 15度， 则 温差 > 15 度时的时长为 30 秒，占比为 50%. 算法:  符合条件的打点数/行程中的打点数。 计算时长，就数点数。

国标采集的是国标的极值。

快冷和慢冷有算法来判断。

SOH 电池健康度，计算估算值。

电渠系统水泵空气占比 即冷却水流量？

PTC 持续时长（开一段时间后才关闭），累积功耗

零部件里面没有出租车和私家车的概念。

