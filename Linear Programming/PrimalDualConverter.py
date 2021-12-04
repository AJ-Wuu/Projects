'''@author AJWuu'''

def converter(c, A, b, M1, M2, M3, N1, N2, N3):
    cc = []
    AA = []
    bb = []
    for i in range(M1+M2+M3):
        cc.append(b[i])
    for i in range(N1, N1+N2):
        tempEquation = []  # (N1+N2+N3) × (M1+M2+M3) matrix
        for j in range(M1+M2+M3):
            tempEquation.append(A[j][i])
        AA.append(tempEquation)
    for i in range(N1):
        tempEquation = []  # (N1+N2+N3) × (M1+M2+M3) matrix
        for j in range(M1+M2+M3):
            tempEquation.append(A[j][i])
        AA.append(tempEquation)
    for i in range(N1+N2, N1+N2+N3):
        tempEquation = []  # (N1+N2+N3) × (M1+M2+M3) matrix
        for j in range(M1+M2+M3):
            tempEquation.append(A[j][i])
        AA.append(tempEquation)
    for i in range(M1+M2+M3):
        tempRestriction = []  # (M1+M2+M3) × (M1+M2+M3) matrix
        for j in range(M1+M2+M3):
            if (i != j):
                tempRestriction.append(0)
            else:
                tempRestriction.append(1)
        AA.append(tempRestriction)
    for i in range(N1, N1+N2):
        bb.append(c[i])
    for i in range(N1):
        bb.append(c[i])
    for i in range(N1+N2, N1+N2+N3):
        bb.append(c[i])
    for i in range(M1+M2+M3):
        bb.append(0)
    return (cc, AA, bb)


def printEquation(coefficients, var, vs, rhs):
    num = len(coefficients)
    equation = "\t"
    for i in range(num):
        equation += str(coefficients[i]) + var + "_" + str(i+1)
        if i != num-1:
            equation += " + "
        else:
            equation += " " + vs + " " + str(rhs)
    print(equation)


def printRestriction(coefficients, var, vs, rhs):
    currVar = -1
    for i in range(len(coefficients)):
        if (coefficients[i] != 0):
            currVar = i
    if (currVar != -1):
        restriction = "\t" + var + \
            "_" + str(currVar+1) + " " + vs + " " + str(rhs)
    else:
        restriction = ""
    print(restriction)


def printer(c, A, b, M1, M2, M3, N1, N2, N3, flag):
    if (flag == True):
        # primal
        print("min")
        printEquation(c, "x", "", "")
        print("s.t.")
        for i in range(M1):
            printEquation(A[i], "x", ">=", b[i])
        for i in range(M1, M1+M2):
            printEquation(A[i], "x", "<=", b[i])
        for i in range(M1+M2, M1+M2+M3):
            printEquation(A[i], "x", "=", b[i])
        for i in range(M1+M2+M3, M1+M2+M3+N1):
            printRestriction(A[i], "x", ">=", b[i])
        for i in range(M1+M2+M3+N1, M1+M2+M3+N1+N2):
            printRestriction(A[i], "x", "<=", b[i])
        print("\n")
    else:
        # dual
        print("max")
        printEquation(c, "p", "", "")
        print("s.t.")
        for i in range(M1):
            printEquation(A[i], "p", ">=", b[i])
        for i in range(M1, M1+M2):
            printEquation(A[i], "p", "<=", b[i])
        for i in range(M1+M2, M1+M2+M3):
            printEquation(A[i], "p", "=", b[i])
        for i in range(M1+M2+M3, M1+M2+M3+N1):
            printRestriction(A[i], "p", ">=", b[i])
        for i in range(M1+M2+M3+N1, M1+M2+M3+N1+N2):
            printRestriction(A[i], "p", "<=", b[i])
        print("\n")


def main():
    M1 = 1  # ai'x >= bi
    M2 = 1  # ai'x <= bi
    M3 = 1  # ai'x = bi
    N1 = 2  # xj >= 0
    N2 = 1  # xj <= 0
    N3 = 1  # xj free
    # A would be Row × Col
    # b would be Row × 1
    # x is Col × 1
    c = [1, -1, 0, 0]
    A = [[3, 1, 4, -2], [2, 3, -1, 1], [-1, -1, 2, 1],
         [0, 1, 0, 0], [0, 0, 1, 0], [1, 0, 0, 0], [0, 0, 0, 0]]
    b = [3, 0, 6, 0, 0, 0, 0]
    printer(c, A, b, M1, M2, M3, N1, N2, N3, flag=True)

    (cc, AA, bb) = converter(c, A, b, M1, M2, M3, N1, N2, N3)
    printer(cc, AA, bb, N2, N1, N3, M1, M2, M3, flag=False)


if __name__ == "__main__":
    main()
