import random
Name = input(print('Enter name: '))
Whtfo = input(print('What is password for?: '))
Pass = input(print('Type (a): '))
Character=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
Number=['1','2','3','4','5','6','7','8','9','0']
symbols=['!','@','#','$','%','^','&','*','(',')','-','=','_','+']
if Pass == 'a':
    first = random.choice(Character)
    second = random.choice(Character)
    thrid =  random.choice(Number)
    fourth = random.choice(Character)
    fith = random.choice(symbols)
    sixth  = random.choice(Character)
    seventh = random.choice(Number)
    eigth = random.choice(symbols)
    ELfin = first + second + thrid + fourth + fith + sixth + seventh + eigth
    print(ELfin)
done = input(print('Hit ENTER when done'))
namey = open('PasswordGen.txt','a')
namey.write(' , ' + Name + ' ' + Whtfo + ' ' + ELfin)
namey.close()