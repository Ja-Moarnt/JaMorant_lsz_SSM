# 学 校：江 西 理 工 大 学
# 学 院：信息工程学院
# 姓名：刘绍珍
# 开发时间：2023/4/12   20:36
import requests
import pandas as pd

url = 'https://mo.jamorantxff.fun/admin/order/AI/WeekGoods'
response = requests.get(url)

data = response.json()['data']

df = pd.DataFrame(data, columns=['userId', 'goodsId', 'carId', 'latitude', 'longitude'])
df.to_csv('service/service_order/src/main/java/com/atguigu/ggkt/order/AI/Train.csv', index=False)
