FROM gradle:7.4-jdk17-alpine
RUN git submodule init && git submodule update

ADD --chown=gradle . /code
WORKDIR /code
EXPOSE 5000
CMD ["gradle", "--stacktrace", "run"]