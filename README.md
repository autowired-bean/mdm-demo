## Deploy guide

Prerequisites:
- Docker: [Download Docker](https://www.docker.com/products/docker-desktop)

Local deploy steps:
1. Clone or download project
```shell
git clone https://github.com/autowired-bean/mdm-demo.git
```

2. Navigate to the project directory
```shell
cd mdm-demo
```

3. Configure ".env" file. Fill empty values and change some if you need
4. Ensure that ports 8080 is free on your local machine
5. Run multi-container application by following command:

```shell
docker compose -f docker-compose.yaml --env-file .env up -d --build
```

After that main service will be accessible at: http://localhost:8080/  
Swagger UI at: http://localhost:8080/swagger-ui/index

6. Enjoy :D