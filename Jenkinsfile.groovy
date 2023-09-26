pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout do código do repositório
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build da imagem Docker
                sh 'docker build -t meu_projeto_web:1.0 .'
            }
        }

        stage('Testes de Segurança com OWASP ZAP') {
            steps {
                // Configurar OWASP ZAP (exemplo)
                sh 'wget https://github.com/zaproxy/zaproxy/releases/download/v2.11.0/ZAP_2.11.0_Linux.tar.gz'
                sh 'tar -xf ZAP_2.11.0_Linux.tar.gz'

                // Iniciar OWASP ZAP em segundo plano
                sh './ZAP_2.11.0/zap.sh -daemon -port 8090 -host 0.0.0.0 &'

                // Executar testes de segurança com OWASP ZAP (exemplo)
                sh './ZAP_2.11.0/zap-cli/zap-cli.py -v quick-scan -t http://localhost:8087'

                // Encerrar OWASP ZAP (exemplo)
                sh './ZAP_2.11.0/zap.sh -cmd -shutdown'
            }
        }

        stage('Deploy') {
            steps {
                // Execução do container Docker
                sh 'docker run -d -p 8087:8087 meu_projeto_web:1.0'
            }
        }

        stage('Notificação via Telegram') {
            steps {
                // Comando para executar o script de notificação via Telegram
                sh 'python telegram_notification.py'
            }
        }
    }
}
