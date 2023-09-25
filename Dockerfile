FROM python:3.9-slim
WORKDIR /app
COPY *.html ./
COPY CSS CSS/
COPY IMG IMG/
EXPOSE 8087
CMD ["python", "-m", "http.server", "8087"]