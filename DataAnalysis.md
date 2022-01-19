```python
import csv
import pandas as pd
import numpy as np

path = "요일별_금.csv"
df = pd.read_csv(path, encoding='cp949')
df
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>기준일자</th>
      <th>승차_호선</th>
      <th>승차_역</th>
      <th>하차_호선</th>
      <th>하차_역</th>
      <th>금</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>4728</td>
    </tr>
    <tr>
      <th>1</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>대림</td>
      <td>4728</td>
    </tr>
    <tr>
      <th>2</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>2호선</td>
      <td>이대</td>
      <td>3693</td>
    </tr>
    <tr>
      <th>3</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>3598</td>
    </tr>
    <tr>
      <th>4</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>2843</td>
    </tr>
    <tr>
      <th>5</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>6416</td>
    </tr>
    <tr>
      <th>6</th>
      <td>20201020</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>2호선</td>
      <td>교대</td>
      <td>6156</td>
    </tr>
    <tr>
      <th>7</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>3029</td>
    </tr>
    <tr>
      <th>8</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>7호선</td>
      <td>대림</td>
      <td>3029</td>
    </tr>
    <tr>
      <th>9</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>2호선</td>
      <td>이대</td>
      <td>1994</td>
    </tr>
    <tr>
      <th>10</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>1899</td>
    </tr>
    <tr>
      <th>11</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>1144</td>
    </tr>
    <tr>
      <th>12</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>4718</td>
    </tr>
    <tr>
      <th>13</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>대림</td>
      <td>2호선</td>
      <td>교대</td>
      <td>4457</td>
    </tr>
    <tr>
      <th>14</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>3496</td>
    </tr>
    <tr>
      <th>15</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>7호선</td>
      <td>대림</td>
      <td>3496</td>
    </tr>
    <tr>
      <th>16</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>2호선</td>
      <td>이대</td>
      <td>2461</td>
    </tr>
    <tr>
      <th>17</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>2366</td>
    </tr>
    <tr>
      <th>18</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>1611</td>
    </tr>
    <tr>
      <th>19</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>5185</td>
    </tr>
    <tr>
      <th>20</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>이대</td>
      <td>2호선</td>
      <td>교대</td>
      <td>4925</td>
    </tr>
    <tr>
      <th>21</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>3112</td>
    </tr>
    <tr>
      <th>22</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>7호선</td>
      <td>대림</td>
      <td>1896</td>
    </tr>
    <tr>
      <th>23</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>2호선</td>
      <td>이대</td>
      <td>2358</td>
    </tr>
    <tr>
      <th>24</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>2262</td>
    </tr>
    <tr>
      <th>25</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>1508</td>
    </tr>
    <tr>
      <th>26</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>5081</td>
    </tr>
    <tr>
      <th>27</th>
      <td>20201020</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>2호선</td>
      <td>교대</td>
      <td>5188</td>
    </tr>
    <tr>
      <th>28</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>2662</td>
    </tr>
    <tr>
      <th>29</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>7호선</td>
      <td>대림</td>
      <td>1165</td>
    </tr>
    <tr>
      <th>30</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>2호선</td>
      <td>이대</td>
      <td>1627</td>
    </tr>
    <tr>
      <th>31</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>1531</td>
    </tr>
    <tr>
      <th>32</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>777</td>
    </tr>
    <tr>
      <th>33</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>4350</td>
    </tr>
    <tr>
      <th>34</th>
      <td>20201020</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>2호선</td>
      <td>교대</td>
      <td>4090</td>
    </tr>
    <tr>
      <th>35</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>5878</td>
    </tr>
    <tr>
      <th>36</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>7호선</td>
      <td>대림</td>
      <td>4381</td>
    </tr>
    <tr>
      <th>37</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>2호선</td>
      <td>이대</td>
      <td>4843</td>
    </tr>
    <tr>
      <th>38</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>4748</td>
    </tr>
    <tr>
      <th>39</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>3993</td>
    </tr>
    <tr>
      <th>40</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>1117</td>
    </tr>
    <tr>
      <th>41</th>
      <td>20201020</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>2호선</td>
      <td>교대</td>
      <td>7306</td>
    </tr>
    <tr>
      <th>42</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>5528</td>
    </tr>
    <tr>
      <th>43</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>7호선</td>
      <td>대림</td>
      <td>4031</td>
    </tr>
    <tr>
      <th>44</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>2호선</td>
      <td>이대</td>
      <td>4493</td>
    </tr>
    <tr>
      <th>45</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>4398</td>
    </tr>
    <tr>
      <th>46</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>3643</td>
    </tr>
    <tr>
      <th>47</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>3호선</td>
      <td>고속터미널</td>
      <td>7216</td>
    </tr>
    <tr>
      <th>48</th>
      <td>20201020</td>
      <td>2호선</td>
      <td>교대</td>
      <td>2호선</td>
      <td>교대</td>
      <td>6956</td>
    </tr>
  </tbody>
</table>
</div>




```python
# 기준일자 컬럼 삭제
df1 = df.drop('기준일자', axis = 1)
df1.head()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>승차_호선</th>
      <th>승차_역</th>
      <th>하차_호선</th>
      <th>하차_역</th>
      <th>금</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>4728</td>
    </tr>
    <tr>
      <th>1</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>대림</td>
      <td>4728</td>
    </tr>
    <tr>
      <th>2</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>2호선</td>
      <td>이대</td>
      <td>3693</td>
    </tr>
    <tr>
      <th>3</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>3598</td>
    </tr>
    <tr>
      <th>4</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>2843</td>
    </tr>
  </tbody>
</table>
</div>




```python
#역 이름 + 호선 합치기
df1['승차역'] = df1['승차_역'] +" "+ df1['승차_호선'].map(str)
df1.head()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>승차_호선</th>
      <th>승차_역</th>
      <th>하차_호선</th>
      <th>하차_역</th>
      <th>금</th>
      <th>승차역</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>4728</td>
      <td>종로3가 1호선</td>
    </tr>
    <tr>
      <th>1</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>대림</td>
      <td>4728</td>
      <td>종로3가 1호선</td>
    </tr>
    <tr>
      <th>2</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>2호선</td>
      <td>이대</td>
      <td>3693</td>
      <td>종로3가 1호선</td>
    </tr>
    <tr>
      <th>3</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>3598</td>
      <td>종로3가 1호선</td>
    </tr>
    <tr>
      <th>4</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>2843</td>
      <td>종로3가 1호선</td>
    </tr>
  </tbody>
</table>
</div>




```python
df1['하차역'] = df1['하차_역'] +" "+ df1['하차_호선'].map(str)
df1.head()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>승차_호선</th>
      <th>승차_역</th>
      <th>하차_호선</th>
      <th>하차_역</th>
      <th>금</th>
      <th>승차역</th>
      <th>하차역</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>1호선</td>
      <td>종로3가</td>
      <td>4728</td>
      <td>종로3가 1호선</td>
      <td>종로3가 1호선</td>
    </tr>
    <tr>
      <th>1</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>대림</td>
      <td>4728</td>
      <td>종로3가 1호선</td>
      <td>대림 7호선</td>
    </tr>
    <tr>
      <th>2</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>2호선</td>
      <td>이대</td>
      <td>3693</td>
      <td>종로3가 1호선</td>
      <td>이대 2호선</td>
    </tr>
    <tr>
      <th>3</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>7호선</td>
      <td>건대입구</td>
      <td>3598</td>
      <td>종로3가 1호선</td>
      <td>건대입구 7호선</td>
    </tr>
    <tr>
      <th>4</th>
      <td>1호선</td>
      <td>종로3가</td>
      <td>5호선</td>
      <td>영등포구청</td>
      <td>2843</td>
      <td>종로3가 1호선</td>
      <td>영등포구청 5호선</td>
    </tr>
  </tbody>
</table>
</div>




```python
df1 = df1.drop(['승차_호선', '승차_역', '하차_호선', '하차_역'], axis = 1) #axis = 1 : 컬럼
df1 = df1[['승차역', '하차역', '금']]
df1
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>승차역</th>
      <th>하차역</th>
      <th>금</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>종로3가 1호선</td>
      <td>종로3가 1호선</td>
      <td>4728</td>
    </tr>
    <tr>
      <th>1</th>
      <td>종로3가 1호선</td>
      <td>대림 7호선</td>
      <td>4728</td>
    </tr>
    <tr>
      <th>2</th>
      <td>종로3가 1호선</td>
      <td>이대 2호선</td>
      <td>3693</td>
    </tr>
    <tr>
      <th>3</th>
      <td>종로3가 1호선</td>
      <td>건대입구 7호선</td>
      <td>3598</td>
    </tr>
    <tr>
      <th>4</th>
      <td>종로3가 1호선</td>
      <td>영등포구청 5호선</td>
      <td>2843</td>
    </tr>
    <tr>
      <th>5</th>
      <td>종로3가 1호선</td>
      <td>고속터미널 3호선</td>
      <td>6416</td>
    </tr>
    <tr>
      <th>6</th>
      <td>종로3가 1호선</td>
      <td>교대 2호선</td>
      <td>6156</td>
    </tr>
    <tr>
      <th>7</th>
      <td>대림 7호선</td>
      <td>종로3가 1호선</td>
      <td>3029</td>
    </tr>
    <tr>
      <th>8</th>
      <td>대림 7호선</td>
      <td>대림 7호선</td>
      <td>3029</td>
    </tr>
    <tr>
      <th>9</th>
      <td>대림 7호선</td>
      <td>이대 2호선</td>
      <td>1994</td>
    </tr>
    <tr>
      <th>10</th>
      <td>대림 7호선</td>
      <td>건대입구 7호선</td>
      <td>1899</td>
    </tr>
    <tr>
      <th>11</th>
      <td>대림 7호선</td>
      <td>영등포구청 5호선</td>
      <td>1144</td>
    </tr>
    <tr>
      <th>12</th>
      <td>대림 7호선</td>
      <td>고속터미널 3호선</td>
      <td>4718</td>
    </tr>
    <tr>
      <th>13</th>
      <td>대림 7호선</td>
      <td>교대 2호선</td>
      <td>4457</td>
    </tr>
    <tr>
      <th>14</th>
      <td>이대 2호선</td>
      <td>종로3가 1호선</td>
      <td>3496</td>
    </tr>
    <tr>
      <th>15</th>
      <td>이대 2호선</td>
      <td>대림 7호선</td>
      <td>3496</td>
    </tr>
    <tr>
      <th>16</th>
      <td>이대 2호선</td>
      <td>이대 2호선</td>
      <td>2461</td>
    </tr>
    <tr>
      <th>17</th>
      <td>이대 2호선</td>
      <td>건대입구 7호선</td>
      <td>2366</td>
    </tr>
    <tr>
      <th>18</th>
      <td>이대 2호선</td>
      <td>영등포구청 5호선</td>
      <td>1611</td>
    </tr>
    <tr>
      <th>19</th>
      <td>이대 2호선</td>
      <td>고속터미널 3호선</td>
      <td>5185</td>
    </tr>
    <tr>
      <th>20</th>
      <td>이대 2호선</td>
      <td>교대 2호선</td>
      <td>4925</td>
    </tr>
    <tr>
      <th>21</th>
      <td>건대입구 7호선</td>
      <td>종로3가 1호선</td>
      <td>3112</td>
    </tr>
    <tr>
      <th>22</th>
      <td>건대입구 7호선</td>
      <td>대림 7호선</td>
      <td>1896</td>
    </tr>
    <tr>
      <th>23</th>
      <td>건대입구 7호선</td>
      <td>이대 2호선</td>
      <td>2358</td>
    </tr>
    <tr>
      <th>24</th>
      <td>건대입구 7호선</td>
      <td>건대입구 7호선</td>
      <td>2262</td>
    </tr>
    <tr>
      <th>25</th>
      <td>건대입구 7호선</td>
      <td>영등포구청 5호선</td>
      <td>1508</td>
    </tr>
    <tr>
      <th>26</th>
      <td>건대입구 7호선</td>
      <td>고속터미널 3호선</td>
      <td>5081</td>
    </tr>
    <tr>
      <th>27</th>
      <td>건대입구 7호선</td>
      <td>교대 2호선</td>
      <td>5188</td>
    </tr>
    <tr>
      <th>28</th>
      <td>영등포구청 5호선</td>
      <td>종로3가 1호선</td>
      <td>2662</td>
    </tr>
    <tr>
      <th>29</th>
      <td>영등포구청 5호선</td>
      <td>대림 7호선</td>
      <td>1165</td>
    </tr>
    <tr>
      <th>30</th>
      <td>영등포구청 5호선</td>
      <td>이대 2호선</td>
      <td>1627</td>
    </tr>
    <tr>
      <th>31</th>
      <td>영등포구청 5호선</td>
      <td>건대입구 7호선</td>
      <td>1531</td>
    </tr>
    <tr>
      <th>32</th>
      <td>영등포구청 5호선</td>
      <td>영등포구청 5호선</td>
      <td>777</td>
    </tr>
    <tr>
      <th>33</th>
      <td>영등포구청 5호선</td>
      <td>고속터미널 3호선</td>
      <td>4350</td>
    </tr>
    <tr>
      <th>34</th>
      <td>영등포구청 5호선</td>
      <td>교대 2호선</td>
      <td>4090</td>
    </tr>
    <tr>
      <th>35</th>
      <td>고속터미널 3호선</td>
      <td>종로3가 1호선</td>
      <td>5878</td>
    </tr>
    <tr>
      <th>36</th>
      <td>고속터미널 3호선</td>
      <td>대림 7호선</td>
      <td>4381</td>
    </tr>
    <tr>
      <th>37</th>
      <td>고속터미널 3호선</td>
      <td>이대 2호선</td>
      <td>4843</td>
    </tr>
    <tr>
      <th>38</th>
      <td>고속터미널 3호선</td>
      <td>건대입구 7호선</td>
      <td>4748</td>
    </tr>
    <tr>
      <th>39</th>
      <td>고속터미널 3호선</td>
      <td>영등포구청 5호선</td>
      <td>3993</td>
    </tr>
    <tr>
      <th>40</th>
      <td>고속터미널 3호선</td>
      <td>고속터미널 3호선</td>
      <td>1117</td>
    </tr>
    <tr>
      <th>41</th>
      <td>고속터미널 3호선</td>
      <td>교대 2호선</td>
      <td>7306</td>
    </tr>
    <tr>
      <th>42</th>
      <td>교대 2호선</td>
      <td>종로3가 1호선</td>
      <td>5528</td>
    </tr>
    <tr>
      <th>43</th>
      <td>교대 2호선</td>
      <td>대림 7호선</td>
      <td>4031</td>
    </tr>
    <tr>
      <th>44</th>
      <td>교대 2호선</td>
      <td>이대 2호선</td>
      <td>4493</td>
    </tr>
    <tr>
      <th>45</th>
      <td>교대 2호선</td>
      <td>건대입구 7호선</td>
      <td>4398</td>
    </tr>
    <tr>
      <th>46</th>
      <td>교대 2호선</td>
      <td>영등포구청 5호선</td>
      <td>3643</td>
    </tr>
    <tr>
      <th>47</th>
      <td>교대 2호선</td>
      <td>고속터미널 3호선</td>
      <td>7216</td>
    </tr>
    <tr>
      <th>48</th>
      <td>교대 2호선</td>
      <td>교대 2호선</td>
      <td>6956</td>
    </tr>
  </tbody>
</table>
</div>




```python
df2 = df1.pivot_table(index='승차역', columns='하차역', values="금")
df2
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th>하차역</th>
      <th>건대입구 7호선</th>
      <th>고속터미널 3호선</th>
      <th>교대 2호선</th>
      <th>대림 7호선</th>
      <th>영등포구청 5호선</th>
      <th>이대 2호선</th>
      <th>종로3가 1호선</th>
    </tr>
    <tr>
      <th>승차역</th>
      <th></th>
      <th></th>
      <th></th>
      <th></th>
      <th></th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>건대입구 7호선</th>
      <td>2262</td>
      <td>5081</td>
      <td>5188</td>
      <td>1896</td>
      <td>1508</td>
      <td>2358</td>
      <td>3112</td>
    </tr>
    <tr>
      <th>고속터미널 3호선</th>
      <td>4748</td>
      <td>1117</td>
      <td>7306</td>
      <td>4381</td>
      <td>3993</td>
      <td>4843</td>
      <td>5878</td>
    </tr>
    <tr>
      <th>교대 2호선</th>
      <td>4398</td>
      <td>7216</td>
      <td>6956</td>
      <td>4031</td>
      <td>3643</td>
      <td>4493</td>
      <td>5528</td>
    </tr>
    <tr>
      <th>대림 7호선</th>
      <td>1899</td>
      <td>4718</td>
      <td>4457</td>
      <td>3029</td>
      <td>1144</td>
      <td>1994</td>
      <td>3029</td>
    </tr>
    <tr>
      <th>영등포구청 5호선</th>
      <td>1531</td>
      <td>4350</td>
      <td>4090</td>
      <td>1165</td>
      <td>777</td>
      <td>1627</td>
      <td>2662</td>
    </tr>
    <tr>
      <th>이대 2호선</th>
      <td>2366</td>
      <td>5185</td>
      <td>4925</td>
      <td>3496</td>
      <td>1611</td>
      <td>2461</td>
      <td>3496</td>
    </tr>
    <tr>
      <th>종로3가 1호선</th>
      <td>3598</td>
      <td>6416</td>
      <td>6156</td>
      <td>4728</td>
      <td>2843</td>
      <td>3693</td>
      <td>4728</td>
    </tr>
  </tbody>
</table>
</div>




```python
LDP_arr=df["금"].values
LDP_arr
```




    array([4728, 4728, 3693, 3598, 2843, 6416, 6156, 3029, 3029, 1994, 1899,
           1144, 4718, 4457, 3496, 3496, 2461, 2366, 1611, 5185, 4925, 3112,
           1896, 2358, 2262, 1508, 5081, 5188, 2662, 1165, 1627, 1531,  777,
           4350, 4090, 5878, 4381, 4843, 4748, 3993, 1117, 7306, 5528, 4031,
           4493, 4398, 3643, 7216, 6956], dtype=int64)




```python
e=2.7183
p=0.5
q=1/(1+e**1)

h=0
b=0

find_original=[]

for j in range(len(LDP_arr)): 
    count=0 
    for k in range(LDP_arr[b]): 
        arr = np.array(h*[0] + [1]+(24-h)*[0]) 
        r1 = 0
        r2 = 0
        for i in range(len(arr)): 
            if arr[i]==1: 
                r1=np.random.rand() 
                if r1 < p:
                    arr[i]=1 
                else:
                    arr[i]=0 
            else:
                r2=np.random.rand()
                if r2 < q:
                    arr[i]=1
                else:
                    arr[i]=0
         # 한 줄 완성    
#         print(arr) # 변형된 arr 한줄 출력
        if arr[j]==1:
            count=count+1
            
    b=b+1
    h=h+1
    print('변형 n값 : ',count)
    original_count=int((count-(LDP_arr[j]*q))/(p-q))
    find_original.append(abs(original_count))
    print('원본 n값 : ',abs(original_count))
    print('find_original : ',find_original)
    print('.....................',j,LDP_arr[j])
```


```python
import pandas as pd
f = [4658, 4528, 3487, 3628, 2732, 6277, 6008, 3143, 3035, 2084, 1931, 1126, 4726, 4394, 3604, 3630, 2402, 2296, 1474, 5078, 4896, 3077, 1601, 2236, 2249, 1594, 5048, 5101, 2670, 1210, 1771, 1563, 822, 4401, 4068, 5808, 4253, 4853, 4778, 3757, 1114, 7193, 5670, 3959, 4529, 4242, 3506, 7177, 7198]
df5 = pd.DataFrame(f)
df5.to_csv("최종.csv")
```
