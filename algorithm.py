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
