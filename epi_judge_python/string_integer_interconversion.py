from test_framework import generic_test
from test_framework.test_failure import TestFailure

CH_TO_DIGIT = {str(i): i for i in range(10)}
DIGIT_TO_CH = {i: str(i) for i in range(10)}

def int_to_string(x) -> str:
    chars = list()
    sign = 1

    if x == 0:
        chars.append(DIGIT_TO_CH[0])
    if x < 0:
        sign = -1
        x *= -1

    while x != 0:
        chars.append(DIGIT_TO_CH[x % 10])
        x //= 10

    if sign == -1:
        chars.append('-')
    return ''.join(reversed(chars))


def string_to_int(s) -> int:
    num = 0
    sign = 1
    for ch in s:
        if ch == '-':
            sign = -1
        else:
            num = num * 10 + CH_TO_DIGIT[ch]

    return num * sign


def wrapper(x, s):
    if int_to_string(x) != s:
        raise TestFailure("Int to string conversion failed")
    if string_to_int(s) != x:
        raise TestFailure("String to int conversion failed")


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main("string_integer_interconversion.py",
                                       'string_integer_interconversion.tsv',
                                       wrapper))
