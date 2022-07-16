package usecase;

public interface UseCaseHandler<E, T extends UseCase> {

    E handle(T t);
}
