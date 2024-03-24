green = "\033[32m"
default = "\033[0m"

# docker-compose.yml를 통해 생성된 모든 서비스를 생성 후, 백그라운드에 실행
# up: 서비스 생성 후, 실행
# -d: 백그라운드 실행
.PHONY: up
up:
	@echo $(green)Executed: docker compose up -d $(default)
	@docker compose up -d

# docker-compose.yml를 통해 생성된 container, network, volume 제거
.PHONY: down
down:
	@echo $(green)Executed: docker compose down $(default)
	@docker compose down


# docker-compose.yml를 통해 생성된 container, network, volume, image 제거
# --rmi all: down 시, 생성된 이미지 제거
.PHONY: clean
clean:
	@echo $(green)Executed: docker compose down --rmi all $(default)
	@docker compose down --rmi all



# docker-compose.yml를 통해 생성된 container, network, image, 모든 익명 volume 제거
# prun -f: 모든 익명 볼륨 제거
.PHONY: fclean
fclean: clean
	@echo $(green)Executed: docker volume prune -a -f $(default)
	@docker volume prune -f


# 모든 container, image, volume 리스트 출력
.PHONY: ps
ps:
	@echo $(green)Executed: docker compose ps $(default)
	@docker compose ps
	@echo $(green)Executed: docker images $(default)
	@docker images
	@echo $(green)Executed: docker volume ls $(default)
	@docker volume ls


# 입력한 서비스의 로그 출력
.PHONY: logs
logs:
	@docker compose ps
	@printf "[logs] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker compose logs $$service $(default); \
	docker compose logs $$service


# 입력한 서비스의 정보 출력
.PHONY: inspect
inspect:
	@docker compose ps
	@printf "[inspect] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker inspect $$service $(default); \
	docker inspect $$service; \


# 입력한 서비스로 접속
.PHONY: exec
exec:
	@docker compose ps
	@printf "[exec] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker compose exec $$service /bin/bash $(default); \
	docker compose exec $$service /bin/bash; \