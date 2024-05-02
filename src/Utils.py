import time, sys, itertools
from termcolor import colored
def Print(string: str, color: str, delay: float, new_line: bool):
    for char in string:
        print(colored(char, color), end='', flush=True)
        time.sleep(delay)  # Adjust the sleep time as needed
    if new_line:
        print()
def Input(string, color):
    user_input = input(colored(string, color))
    return user_input
def spinning_cursor(Range: int, delay: int):
    cursor = itertools.cycle('|/-\\')
    for _ in range(Range):
        sys.stdout.write(next(cursor))
        sys.stdout.flush()
        time.sleep(delay)
        sys.stdout.write('\b')
def draw_bar_chart(data, color):
    max_value = max(data.values())
    for key, value in data.items():
        bar_length = int(value / max_value * 30)  # Scale the length of the bar
        Print((f"{key}: {'#' * bar_length}"), color, 0.05, True)
def loading_bar(iterations, color, message, delay=0.1):
    for i in range(iterations):
        sys.stdout.write('\r')
        sys.stdout.write(colored(message + "[%-20s] %d%%" % ('=' * (i % 21), 5 * i), color))
        sys.stdout.flush()
        time.sleep(delay)
if __name__ == "__main__":
    Print("---Loading Bar---", "blue", 0.025, True)
    loading_bar(21, "red", "Testing")
    print("")
    Print("---Colors---", "blue", 0.025, True)
    color_list = ["black", "grey", "red", "green",
                  "yellow", "blue", "magenta", "cyan",
                  "light_grey", "dark_grey", "light_red",
                  "light_green", "light_yellow", "light_blue",
                  "light_magenta", "light_cyan", "white"]
    for i in range(len(color_list)):
        Print(f"Color: {color_list[i]}", color_list[i], 0.025, True)
    print("")
    Print("---Spinning Cursor---", "blue", 0.025, True)
    spinning_cursor(50, 0.1)
    print("")
    Print("---Bar Chart---", "blue", 0.025, True)
    data = {"A": 20, "B": 20, "C": 15, "D": 25}
    draw_bar_chart(data, "yellow")