


export type Fails<T> = (T & { isOk: true }) | (T & { isOk: false, message?: string });

export type Result<T, E> = (T & { isOk: true }) | (E & { isOk: false});