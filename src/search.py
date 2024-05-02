import os

def searchFolder(user_input):
    searched_files = []
    
    for root, dirs, files in os.walk('data'):
        for file in files:
            if user_input in file.split()[0]:
                searched_files.append(file)
    return searched_files

def printContents(selectedFile):
    for root, dirs, files in os.walk('data'):
        for file in files:
            if selectedFile == file:
                filepath = root + '/' + file
                f = open(filepath, "r")
                for line in f:
                    print(line)

if __name__ == "__main__":
    user_input = input("Please input keyword: ")
    files = searchFolder(user_input)

    print('---Selected files---')
    fileDict = {}
    count = 0
    for i in files:
        fileDict[count] = i
        print(f"{count}: {i}")
        count += 1

    fileToDisplay = input("\nSelect file number: ")
    print(f"\n---{fileDict[int(fileToDisplay)]} contents---")
    printContents(fileDict[int(fileToDisplay)])