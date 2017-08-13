#include <iostream>
#include <string>
#include <random>
#include <fstream>
using namespace std;

int randomNumber(int min, int max) {
    mt19937 getter;
    getter.seed(random_device()());
    uniform_int_distribution<mt19937::result_type> dist(min, max);
    return dist(getter);
}

int main() {
    string Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_+={}[]|\\;:\"<>,./?";

    // Input
    int numberOfPasswords;
    char fileName[100];
    cout << "Number of passwords: ";
    cin >> numberOfPasswords;
    cout << "File name: ";
    cin >> fileName;

    // Generate passwords
    ofstream output;
    output.open(fileName);
    for (int i = 0; i < numberOfPasswords; i++) {
	for (int charIndex = 0; charIndex < randomNumber(8, 12); charIndex++) {
	   output << Chars[randomNumber(0, Chars.length() - 1)];
	}
	output << endl;
    }
}
