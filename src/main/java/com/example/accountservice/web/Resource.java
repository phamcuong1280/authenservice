package com.example.applicationgateway.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resource<T> {
    private MetaResource meta;
    private T data;

    public Resource(String msg, int code) {
        this.meta = new MetaResource(code, msg, (IPaginationResource) null);
    }

    public Resource(String msg, int code, PaginationResource pages) {
        this.meta = new MetaResource(code, msg, pages);
    }

    public Resource(String msg, int code, IPaginationResource pages) {
        this.meta = new MetaResource(code, msg, pages);
    }

    public Resource(T data) {
        this.meta = new MetaResource(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), (IPaginationResource) null);
        this.data = data;
    }

    public Resource(T data, PaginationResource pages) {
        this.meta = new MetaResource(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), pages);
        this.data = data;
    }

    public Resource(T data, IPaginationResource pages) {
        this.meta = new MetaResource(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), pages);
        this.data = data;
    }

    public Resource() {
    }

    public MetaResource getMeta() {
        return this.meta;
    }

    public void setMeta(final MetaResource meta) {
        this.meta = meta;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Resource)) {
            return false;
        } else {
            Resource<?> other = (Resource) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$meta = this.getMeta();
                Object other$meta = other.getMeta();
                if (this$meta == null) {
                    if (other$meta != null) {
                        return false;
                    }
                } else if (!this$meta.equals(other$meta)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Resource;
    }

    public int hashCode() {
        int result = 1;
        Object $meta = this.getMeta();
        result = result * 59 + ($meta == null ? 43 : $meta.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "Resource(meta=" + this.getMeta() + ", data=" + this.getData() + ")";
    }
}

