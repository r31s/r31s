
#include <stdio.h>
#include <stdlib.h>

// Funções separadas para cada operação
int soma(int a, int b) {
    return a + b;
}

int subtracao(int a, int b) {
    return a - b;
}

int multiplicacao(int a, int b) {
    return a * b;
}

float divisao(int a, int b) {
    return (float)a / b;
}

int modulo(int a, int b) {
    return a % b;
}

int main() {
    int numA, numB;
    char operador;
    char continuar;

    do {
        printf("\n--- CALCULADORA EM C ---\n");
        printf("Digite o primeiro numero: ");
        scanf("%d", &numA);

        printf("Digite o segundo numero: ");
        scanf("%d", &numB);

        printf("Escolha uma operacao (+, -, *, /, %%): ");
        scanf(" %c", &operador);

        switch (operador) {
            case '+':
                printf("Resultado: %d + %d = %d\n", numA, numB, soma(numA, numB));
                break;
            case '-':
                printf("Resultado: %d - %d = %d\n", numA, numB, subtracao(numA, numB));
                break;
            case '*':
                printf("Resultado: %d * %d = %d\n", numA, numB, multiplicacao(numA, numB));
                break;
            case '/':
                if (numB != 0) {
                    printf("Resultado: %d / %d = %.2f\n", numA, numB, divisao(numA, numB));
                } else {
                    printf("Erro: divisao por zero nao permitida.\n");
                }
                break;
            case '%':
                if (numB != 0) {
                    printf("Resultado: %d %% %d = %d\n", numA, numB, modulo(numA, numB));
                } else {
                    printf("Erro: modulo por zero nao permitido.\n");
                }
                break;
            default:
                printf("Operador invalido!\n");
        }

        printf("Deseja realizar outra operacao? (s/n): ");
        scanf(" %c", &continuar);

    } while (continuar == 's' || continuar == 'S');

    printf("Encerrando o programa. Ate logo!\n");

    return 0;
}
