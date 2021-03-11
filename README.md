How to build
buid image first using
docker build -t studentreport .

and compose them using
docker-compose up --build -d

How to login
hit http://localhost:8080/api/generate (using GET) first to generate admin account
login at http://localhost:8080/api/users/login (using POST) using this request

{
	"username":"admin",
	"password":"admin"
}

you will get token string to get access to all api
