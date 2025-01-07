package com.ljava.auth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@ConfigurationProperties(
        prefix = "spring.redis"
)
public class RedisProperties {
    private int database = 0;
    private String url;
    private String host = "localhost";
    private String username;
    private String password;
    private int port = 6379;
    private boolean ssl;
    private Duration timeout;
    private Duration connectTimeout;
    private String clientName;
    private org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType clientType;
    private org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel sentinel;
    private org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster cluster;
    private final org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis jedis = new org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis();
    private final org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce lettuce = new org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce();

    public RedisProperties() {
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSsl() {
        return this.ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public Duration getTimeout() {
        return this.timeout;
    }

    public Duration getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType getClientType() {
        return this.clientType;
    }

    public void setClientType(org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType clientType) {
        this.clientType = clientType;
    }

    public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel getSentinel() {
        return this.sentinel;
    }

    public void setSentinel(org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel sentinel) {
        this.sentinel = sentinel;
    }

    public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster getCluster() {
        return this.cluster;
    }

    public void setCluster(org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster cluster) {
        this.cluster = cluster;
    }

    public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis getJedis() {
        return this.jedis;
    }

    public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce getLettuce() {
        return this.lettuce;
    }

    public static class Lettuce {
        private Duration shutdownTimeout = Duration.ofMillis(100L);
        private final org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool pool = new org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool();
        private final org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster cluster = new org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster();

        public Lettuce() {
        }

        public Duration getShutdownTimeout() {
            return this.shutdownTimeout;
        }

        public void setShutdownTimeout(Duration shutdownTimeout) {
            this.shutdownTimeout = shutdownTimeout;
        }

        public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool getPool() {
            return this.pool;
        }

        public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster getCluster() {
            return this.cluster;
        }

        public static class Cluster {
            private final org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh refresh = new org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh();

            public Cluster() {
            }

            public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh getRefresh() {
                return this.refresh;
            }

            public static class Refresh {
                private boolean dynamicRefreshSources = true;
                private Duration period;
                private boolean adaptive;

                public Refresh() {
                }

                public boolean isDynamicRefreshSources() {
                    return this.dynamicRefreshSources;
                }

                public void setDynamicRefreshSources(boolean dynamicRefreshSources) {
                    this.dynamicRefreshSources = dynamicRefreshSources;
                }

                public Duration getPeriod() {
                    return this.period;
                }

                public void setPeriod(Duration period) {
                    this.period = period;
                }

                public boolean isAdaptive() {
                    return this.adaptive;
                }

                public void setAdaptive(boolean adaptive) {
                    this.adaptive = adaptive;
                }
            }
        }
    }

    public static class Jedis {
        private final org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool pool = new org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool();

        public Jedis() {
        }

        public org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool getPool() {
            return this.pool;
        }
    }

    public static class Sentinel {
        private String master;
        private List<String> nodes;
        private String username;
        private String password;

        public Sentinel() {
        }

        public String getMaster() {
            return this.master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Cluster {
        private List<String> nodes;
        private Integer maxRedirects;

        public Cluster() {
        }

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public Integer getMaxRedirects() {
            return this.maxRedirects;
        }

        public void setMaxRedirects(Integer maxRedirects) {
            this.maxRedirects = maxRedirects;
        }
    }

    public static class Pool {
        private Boolean enabled;
        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 8;
        private Duration maxWait = Duration.ofMillis(-1L);
        private Duration timeBetweenEvictionRuns;

        public Pool() {
        }

        public Boolean getEnabled() {
            return this.enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public int getMaxIdle() {
            return this.maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return this.minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return this.maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public Duration getMaxWait() {
            return this.maxWait;
        }

        public void setMaxWait(Duration maxWait) {
            this.maxWait = maxWait;
        }

        public Duration getTimeBetweenEvictionRuns() {
            return this.timeBetweenEvictionRuns;
        }

        public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
            this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
        }
    }

    public static enum ClientType {
        LETTUCE,
        JEDIS;

        private ClientType() {
        }
    }

}
