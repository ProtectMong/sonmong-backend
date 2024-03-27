echo "[Log] Set prod env"
source .env.prod

echo "[Log] Docker login"
echo "${DOCKER_PASSWORD}" | sudo docker login --username ${DOCKER_USERNAME} --password-stdin

echo "[Log] Start DB"
is_db_running=$(docker ps -a | grep mysql | awk '{print $1}')
if [[ -z $is_db_running ]]; then
        make db
fi

echo "[Log] docker compose stop app"
sudo docker compose stop app
echo "[Log] docker compose rm -f app"
sudo docker compose rm -f app
echo "[Log] docker pull app"
sudo docker pull ${DOCKER_USERNAME}/${DOCKER_REPOSITORY}
echo "[Log] docker compose up -d app"
make app
echo "[Log] docker image prune -f"
sudo docker image prune -f