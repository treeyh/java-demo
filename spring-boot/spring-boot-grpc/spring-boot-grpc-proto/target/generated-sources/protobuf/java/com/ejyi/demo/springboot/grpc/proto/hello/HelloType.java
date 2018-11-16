// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hello.proto

package com.ejyi.demo.springboot.grpc.proto.hello;

/**
 * Protobuf enum {@code helloworld.HelloType}
 */
public enum HelloType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>Type1 = 0;</code>
   */
  Type1(0),
  /**
   * <code>Type2 = 1;</code>
   */
  Type2(1),
  /**
   * <code>Type3 = 2;</code>
   */
  Type3(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>Type1 = 0;</code>
   */
  public static final int Type1_VALUE = 0;
  /**
   * <code>Type2 = 1;</code>
   */
  public static final int Type2_VALUE = 1;
  /**
   * <code>Type3 = 2;</code>
   */
  public static final int Type3_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static HelloType valueOf(int value) {
    return forNumber(value);
  }

  public static HelloType forNumber(int value) {
    switch (value) {
      case 0: return Type1;
      case 1: return Type2;
      case 2: return Type3;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<HelloType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      HelloType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<HelloType>() {
          public HelloType findValueByNumber(int number) {
            return HelloType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.ejyi.demo.springboot.grpc.proto.hello.HelloProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final HelloType[] VALUES = values();

  public static HelloType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private HelloType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helloworld.HelloType)
}
