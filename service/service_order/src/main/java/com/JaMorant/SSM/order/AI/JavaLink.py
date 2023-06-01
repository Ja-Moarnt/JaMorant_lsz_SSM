import torch
import torch.nn as nn
import torch.optim as optim
import pandas as pd
import warnings

warnings.filterwarnings("ignore", message="loaded more than 1 DLL from .libs")

# 定义神经网络模型
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.fc1 = nn.Linear(4, 64)  # 输入层到第一层隐藏层
        self.fc2 = nn.Linear(64, 64)  # 第一层隐藏层到第二层隐藏层
        self.fc3 = nn.Linear(64, 1)  # 第二层隐藏层到输出层
        self.dropout = nn.Dropout(p=0.2)  # dropout层

    def forward(self, x):
        x = nn.functional.relu(self.fc1(x))  # 将输入x通过第一个全连接层fc1，并使用ReLU激活函数进行非线性变换。ReLU激活函数可以保留正数的值不变，将负数的值变为0。
        x = self.dropout(x)
        x = nn.functional.relu(self.fc2(x))
        x = self.dropout(x)
        x = self.fc3(x)
        return x
'''
这个模型有三个全连接层，一个dropout层，以及relu激活函数。模型的输入为一个大小为4的张量，输出为一个标量。
在模型的初始化过程中，通过nn.Linear()方法定义了三个全连接层，分别是输入层到第一层隐藏层、第一层隐藏层到第二层隐藏层、第二层隐藏层到输出层。
其中，第一个参数是输入特征的维度，第二个参数是输出特征的维度。这里输入特征的维度为4，输出特征的维度为64、64、1。同时，还定义了一个dropout层，用于在训练过程中随机丢弃一部分神经元，防止过拟合。
在模型的前向传播过程中，首先将输入张量x传入第一层全连接层，并通过relu激活函数得到第一层的输出，然后将其传入dropout层进行随机丢弃。
接着，将dropout层的输出传入第二层全连接层，并再次通过relu激活函数得到第二层的输出，然后再次传入dropout层进行随机丢弃。最后，将dropout层的输出传入输出层，得到模型的预测结果。
整个过程可以通过nn.functional库中的relu()函数实现。
'''

# 加载训练数据
train_df = pd.read_csv('service/service_order/src/main/java/com/atguigu/ggkt/order/AI/Train.csv')

# 准备训练数据
x_train = train_df[['userId', 'carId', 'latitude', 'longitude']].values.astype(float)
y_train = train_df['goodsId'].values.astype(float)

# 标准化训练数据
mean = x_train.mean(axis=0)
std = x_train.std(axis=0)
x_train = (x_train - mean) / std

# 定义神经网络模型
net = Net()

# 定义损失函数和优化器
criterion = nn.MSELoss()
# 定义优化器optimizer，并将其初始化为Adam优化器。
# Adam是一种常用的优化算法，它在梯度下降的基础上引入了动量(momentum)和自适应学习率(adaptive learning rate)等机制，从而更快地优化模型。
optimizer = optim.Adam(net.parameters(), lr=0.01)

# 训练神经网络模型
for epoch in range(100):
    inputs = torch.from_numpy(x_train).float()
    targets = torch.from_numpy(y_train).float()
    optimizer.zero_grad()
    outputs = net(inputs)
    loss = criterion(outputs, targets.unsqueeze(1))
    loss.backward()
    optimizer.step()
    # if epoch % 10 == 0:
    #     print('Epoch {}, Loss: {}'.format(epoch, loss.item()))
'''
for循环遍历100个epoch，即100次训练迭代。
使用torch.from_numpy()将numpy数组转换为PyTorch张量。
使用optimizer.zero_grad()清除梯度信息。
使用net()进行前向传播，得到模型的预测结果outputs。
使用criterion()计算预测结果和真实标签之间的损失。
使用loss.backward()计算损失相对于模型参数的梯度信息。
使用optimizer.step()根据梯度信息更新模型参数。
使用print()语句输出当前epoch的损失信息。
'''
# 加载测试数据
test_df = pd.read_csv('service/service_order/src/main/java/com/atguigu/ggkt/order/AI/Test.csv')

# 准备测试数据
x_test = test_df[['userId', 'carId', 'latitude', 'longitude']].values.astype(float)

# 标准化测试数据
x_test = (x_test - mean) / std

# 使用训练好的神经网络模型进行预测
inputs = torch.from_numpy(x_test).float()  # 将测试数据x_test转换为PyTorch张量，并设置数据类型为float类型
outputs = net(inputs)  # 将测试数据inputs输入到神经网络模型net中进行前向传播，并得到模型的输出outputs
# 将神经网络模型的输出张量outputs转换为numpy数组，然后对其进行取整(round)操作并将结果转换为整数数据类型(int)，从而得到最终的预测结果y_pred
y_pred = outputs.detach().numpy().round().astype(int)


print("Forecast results:")

# 输出结果

# 将预测结果保存到csv文件中
test_df['goodsId'] = y_pred
test_df.to_csv('service/service_order/src/main/java/com/atguigu/ggkt/order/AI/predictions.csv', index=False)

