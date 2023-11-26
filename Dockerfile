FROM python:3.8-bullseye

RUN mkdir /usr/local/nvm
ENV NVM_DIR /usr/local/nvm
ENV NODE_VERSION 16.18.1
RUN curl https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash \
    && . $NVM_DIR/nvm.sh \
    && nvm install $NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default


# Create app directory
RUN mkdir -p /usr/src/project
WORKDIR /usr/src/project

COPY . /usr/src/project

RUN pip install -r requirements.txt


