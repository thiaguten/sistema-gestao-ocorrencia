##### Stage 1
FROM node:lts-alpine as node
WORKDIR /app
ENV TZ=America/Sao_Paulo
COPY ["package.json", "package-lock.json*", "./"]
RUN npm install
#RUN npm install --silent
RUN npm fund
COPY . .
# Angular 12+ does a production build by default if you've enabled it using
# ng update @angular/cli --migrate-only production-by-default
# https://github.com/angular/angular-cli/issues/21073#issuecomment-855960826
# RUN npm run build

# Prod build if production-by-default hasn't been enabled
RUN npm run build -- --prod

##### Stage 2
FROM nginx:alpine
VOLUME /var/cache/nginx
ENV TZ=America/Sao_Paulo
#COPY --from=node /app/dist /usr/share/nginx/html
COPY --from=node /app/dist/portal-web /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf

# Image name: thiaguten/sgo-${npm_package_name}:${npm_package_version}
# Add these Docker commands as scripts to your package.json file.
# docker build -t thiaguten/sgo-portal-web:0.0.0 .
# docker run -p 4200:80 thiaguten/sgo-portal-web:0.0.0
