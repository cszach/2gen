#include <iostream>
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
    int min;
    int max;
    int numberOfPins;
    char fileName[50];

    // Get input for minimum, maximum, number of pins to create, and file to write's name
    cout << "Min: ";
    cin >> min;
    cout << "Max: ";
    cin >> max;
    cout << "Number of pins: ";
    cin >> numberOfPins;
    cout << "File name to write: ";
    cin >> fileName;

    // Generate and write to file
    ofstream output;
    output.open(fileName);
    for (int i = 0; i < numberOfPins; i++) {
        output << randomNumber(min, max) << endl;
    }
    output.close();
}
