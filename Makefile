green = "\033[32m"
default = "\033[0m"

# make 방지
.PHONY: all
all:

# app 컨테이너 생성 후, 실행
.PHONY: app
app:
	@echo $(green)Executed: docker compose up -d app $(default)
	@docker compose up -d app

# db 컨테이너 생성 후, 실행
.PHONY: mysql_db
mysql_db:
	@echo $(green)Executed: docker compose up -d mysql_db $(default)
	@docker compose up -d mysql_db

## docker-compose.yml를 통해 생성된 container, network, volume 제거
#.PHONY: down
#down:
#	@echo $(green)Executed: docker compose down $(default)
#	@docker compose down

## docker-compose.yml를 통해 생성된 container, network, volume, image 제거
## --rmi all: down 시, 생성된 이미지 제거
#.PHONY: clean
#clean:
#	@echo $(green)Executed: docker compose down --rmi all $(default)
#	@docker compose down --rmi all

## docker-compose.yml를 통해 생성된 container, network, image, 모든 익명 volume 제거
## prun -f: 모든 익명 볼륨 제거
#.PHONY: fclean
#fclean:
#	@echo $(green)Executed: docker compose down --rmi all -v $(default)
#	@docker compose down --rmi all -v

# 입력한 서비스의 로그 출력
.PHONY: logs
logs:
	@docker compose ps -a
	@printf "[logs] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker compose logs $$service $(default); \
	docker compose logs -f $$service


# 입력한 서비스의 정보 출력
.PHONY: inspect
inspect:
	@docker compose ps -a
	@printf "[inspect] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker inspect $$service $(default); \
	docker inspect $$service | less; \

# 입력한 서비스로 접속
.PHONY: exec
exec:
	@docker compose ps
	@printf "[exec] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker compose exec $$service /bin/bash $(default); \
	docker compose exec $$service /bin/bash;\

# 모든 container, image, volume 리스트 출력
.PHONY: ps
ps:
	@echo $(green)Executed: docker compose ps $(default)
	@docker compose ps -a
	@echo ""
	@make images
	@echo ""
	@make volumes

# 모든 컨테이너 출력
.PHONY: containers
containers:
	@echo $(green)Executed: docker volume container ls -a$(default)
	@docker container ls -a

# 모든 이미지 출력
.PHONY: images
images:
	@echo $(green)Executed: docker image ls -a $(default)
	@docker image ls -a

# 모든 볼륨 출력
.PHONY: volumes
volumes:
	@echo $(green)Executed: docker volume ls $(default)
	@docker volume ls
