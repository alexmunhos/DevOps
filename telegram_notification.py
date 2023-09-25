import requests

telegram_bot_token = '6680186049:AAHDKQ-W9_P-jSDci6UTYJx573hp6XFG93w'
telegram_chat_id = '6443237468'

def send_telegram_message(message):
    telegram_api_url = f'https://api.telegram.org/bot{telegram_bot_token}/sendMessage'
    data = {
        'chat_id': telegram_chat_id,
        'text': message
    }

    try:
        response = requests.post(telegram_api_url, data=data)
        response.raise_for_status()
        print('Mensagem enviada com sucesso para o Telegram')
    except requests.exceptions.RequestException as e:
        print(f'Erro ao enviar mensagem para o Telegram: {str(e)}')

if __name__ == '__main__':
    message = "Foi realizado um Pull Request(PR)."
    send_telegram_message(message)